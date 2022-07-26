package com.github.vladimirlisovskij.simple_mocker.ui.request_editor

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vladimirlisovskij.simple_mocker.shared.dto.EditedRequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.NewRequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestParams
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.*
import com.github.vladimirlisovskij.simple_mocker.ui.R
import com.github.vladimirlisovskij.simple_mocker.ui.base.ScreenEvent
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Constants
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RequestEditorViewModel(
    private val itemId: Long,
    private val context: Context,
    private val createMockRequestUseCase: CreateMockRequestUseCase,
    private val getRequestByIdUseCase: GetRequestByIdUseCase,
    private val getUriForFileNameUseCase: GetUriForFileNameUseCase,
    private val getFileNameByUriUseCase: GetFileNameByUriUseCase,
    private val updateRequestUseCase: UpdateRequestUseCase
) : ViewModel() {
    private val isInEditMode = itemId != Constants.NO_ID
    private var initialRequestInfo: RequestInfo? = null
    private var selectedFileUri: Uri? = null

    private val mutableScreenState = MutableStateFlow(createInitState())
    val screenState = mutableScreenState.asStateFlow()

    private val mutableScreenEvents = MutableSharedFlow<ScreenEvent>()
    val screenEvent = mutableScreenEvents.asSharedFlow()

    fun onViewCreated() {
        if (isInEditMode && initialRequestInfo == null) {
            viewModelScope.launch {
                val requestInfo = getRequestByIdUseCase.invoke(itemId)
                initialRequestInfo = requestInfo
                mutableScreenState.update { state ->
                    state.copy(
                        isLoading = false,
                        isShowButtonVisible = true,
                        path = requestInfo.requestParams.path,
                        host = requestInfo.requestParams.host.orEmpty(),
                        params = requestInfo.requestParams.params.orEmpty(),
                    )
                }
            }
        }
    }

    fun onFileSelected(fileUri: Uri) {
        selectedFileUri = fileUri
        mutableScreenState.update { state ->
            state.copy(
                fileName = getFileNameByUriUseCase.invoke(fileUri).orEmpty()
            )
        }
    }

    fun onSaveClicked() {
        val curState = mutableScreenState.value
        if (isInEditMode) {
            val errorMessage = checkFieldsForEditMode(curState)
            if (errorMessage != null) {
                viewModelScope.launch {
                    mutableScreenEvents.emit(ToastEvent(errorMessage))
                }
            } else {
                val editedRequestInfo = EditedRequestInfo(
                    id = itemId,
                    requestParams = createRequestParams(curState),
                    newFileUri = selectedFileUri
                )

                viewModelScope.launch {
                    mutableScreenState.update { state ->
                        state.copy(isLoading = true)
                    }

                    updateRequestUseCase.invoke(editedRequestInfo)
                    mutableScreenState.update { state ->
                        state.copy(isLoading = false)
                    }

                    mutableScreenEvents.emit(NavigationBackEvent)
                }
            }
        } else {
            val errorMessage = checkFieldsForCreateMode(curState)
            if (errorMessage != null) {
                viewModelScope.launch {
                    mutableScreenEvents.emit(ToastEvent(errorMessage))
                }
            } else {
                val newRequestInfo = NewRequestInfo(
                    requestParams = createRequestParams(curState),
                    fileUri = selectedFileUri!!
                )

                viewModelScope.launch {
                    mutableScreenState.update { state ->
                        state.copy(isLoading = true)
                    }

                    createMockRequestUseCase.invoke(newRequestInfo)
                    mutableScreenState.update { state ->
                        state.copy(isLoading = false)
                    }

                    mutableScreenEvents.emit(NavigationBackEvent)
                }
            }
        }
    }

    fun onFileShowClicked() {
        initialRequestInfo?.bodyFileName?.let { name ->
            val uri = getUriForFileNameUseCase.invoke(name)
            viewModelScope.launch {
                val jsonIntent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    setDataAndType(uri, "text/plain")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                val chooserIntent = Intent.createChooser(jsonIntent, "title")
                mutableScreenEvents.emit(ShowFileEvent(chooserIntent))
            }
        }
    }

    fun onHostChanged(value: String) {
        mutableScreenState.update { state ->
            state.copy(host = value)
        }
    }

    fun onPathChanged(value: String) {
        mutableScreenState.update { state ->
            state.copy(path = value)
        }
    }

    fun onParamsChanged(value: String) {
        mutableScreenState.update { state ->
            state.copy(params = value)
        }
    }

    private fun createRequestParams(viewState: ViewState): RequestParams {
        return RequestParams(
            path = viewState.path,
            host = viewState.host.ifBlank { null },
            params = if (viewState.params.isNotBlank()) sortParams(viewState.params) else null
        )
    }

    private fun sortParams(string: String): String {
        return string
            .split("&")
            .sorted()
            .joinToString("&")
    }

    private fun createInitState(): ViewState {
        return ViewState(
            isLoading = isInEditMode,
            host = "",
            path = "",
            params = "",
            fileName = "",
            isShowButtonVisible = false
        )
    }

    private fun checkFieldsForEditMode(viewState: ViewState): String? {
        return when {
            viewState.path.isBlank() -> context.getString(R.string.request_editor_empty_path)
            else -> null
        }
    }

    private fun checkFieldsForCreateMode(viewState: ViewState): String? {
        return when {
            viewState.path.isBlank() -> context.getString(R.string.request_editor_empty_path)
            selectedFileUri == null -> context.getString(R.string.request_editor_empty_file)
            else -> null
        }
    }

    class ShowFileEvent(val intent: Intent) : ScreenEvent

    class ToastEvent(val text: String) : ScreenEvent

    object NavigationBackEvent: ScreenEvent

    data class ViewState(
        val isLoading: Boolean,
        val host: String,
        val path: String,
        val params: String,
        val fileName: String,
        val isShowButtonVisible: Boolean
    )

    class Factory(
        private val context: Context,
        private val createMockRequestUseCase: CreateMockRequestUseCase,
        private val getRequestById: GetRequestByIdUseCase,
        private val getUriForFileNameUseCase: GetUriForFileNameUseCase,
        private val getFileNameByUriUseCase: GetFileNameByUriUseCase,
        private val updateRequestUseCase: UpdateRequestUseCase
    ) {
        fun create(itemId: Long) = RequestEditorViewModel(
            itemId,
            context,
            createMockRequestUseCase,
            getRequestById,
            getUriForFileNameUseCase,
            getFileNameByUriUseCase,
            updateRequestUseCase
        )
    }
}