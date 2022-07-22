package com.high_quality_solution.simplemocker.ui.request_editor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.high_quality_solution.simplemocker.shared.dto.NewRequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.usecase.CreateMockRequestUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetFileNameByUriUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetRequestByIdUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetUriForFileNameUseCase
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

        val newRequestInfo = NewRequestInfo(
            requestParams = RequestParams(
                path = "/api/service/cards"
            ),

            fileUri = fileUri
        )

        viewModelScope.launch {
            createMockRequestUseCase.invoke(newRequestInfo)
        }
    }

    fun onSaveClicked() {
        var curState = mutableScreenState.value
        if (isInEditMode) {

        } else {

        }
    }

    private fun checkFieldsForEditMode(viewState: ViewState) {

    }

    private fun checkFieldsForCreateMode(viewState: ViewState) {

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

    class ShowFileEvent(val intent: Intent) : ScreenEvent

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