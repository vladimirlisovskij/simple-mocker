package com.high_quality_solution.simplemocker.ui.request_editor

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.high_quality_solution.simplemocker.okhttp.MockerInterceptor
import com.high_quality_solution.simplemocker.shared.dto.NewRequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.usecase.CreateMockRequestUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetFileNameByUriUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetRequestByIdUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetUriForFileNameUseCase
import com.high_quality_solution.simplemocker.ui.R
import com.high_quality_solution.simplemocker.ui.base.ScreenEvent
import com.high_quality_solution.simplemocker.ui.utils.Constants
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RequestEditorViewModel(
    private val itemId: Long,
    private val context: Context,
    private val createMockRequestUseCase: CreateMockRequestUseCase,
    private val getRequestByIdUseCase: GetRequestByIdUseCase,
    private val getUriForFileNameUseCase: GetUriForFileNameUseCase,
    private val getFileNameByUriUseCase: GetFileNameByUriUseCase
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
                    mutableScreenEvents.emit(ToastScreenEvent(errorMessage))
                }
            } else {

            }
        } else {
            val errorMessage = checkFieldsForCreateMode(curState)
            if (errorMessage != null) {
                viewModelScope.launch {
                    mutableScreenEvents.emit(ToastScreenEvent(errorMessage))
                }
            } else {
                val newRequestInfo = NewRequestInfo(
                    requestParams = RequestParams(
                        path = curState.path,
                        host = curState.host.ifBlank { null },
                        params = if (curState.params.isNotBlank()) sortParams(curState.params) else null
                    ),

                    fileUri = selectedFileUri!!
                )

                viewModelScope.launch {
                    createMockRequestUseCase.invoke(newRequestInfo)
                }
            }
        }
    }

    fun onFileShowClicked() {
        initialRequestInfo?.bodyFileName?.let { name ->
            val uri = getUriForFileNameUseCase.invoke(name)
            viewModelScope.launch {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = uri
                    type = Constants.JSON_MIME
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                mutableScreenEvents.emit(ShowFileEvent(intent))
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

    class ToastScreenEvent(val text: String) : ScreenEvent

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
        private val getFileNameByUriUseCase: GetFileNameByUriUseCase
    ) {
        fun create(itemId: Long) = RequestEditorViewModel(
            itemId,
            context,
            createMockRequestUseCase,
            getRequestById,
            getUriForFileNameUseCase,
            getFileNameByUriUseCase
        )
    }
}