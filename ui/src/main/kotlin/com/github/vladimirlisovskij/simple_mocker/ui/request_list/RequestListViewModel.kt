package com.github.vladimirlisovskij.simple_mocker.ui.request_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.GetRequestListUseCase
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.RemoveRequestUseCase
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.SetRequestEnabledStateUseCase
import com.github.vladimirlisovskij.simple_mocker.ui.base.ScreenEvent
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.RequestListItem
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RequestListViewModel(
    private val getRequestListUseCase: GetRequestListUseCase,
    private val setRequestEnabledStateUseCase: SetRequestEnabledStateUseCase,
    private val removeRequestUseCase: RemoveRequestUseCase
) : ViewModel() {
    val requestsList = getRequestListUseCase
        .invoke()
        .map { it.map(::createRequestListItem) }
        .flowOn(Dispatchers.IO)

    private val mutableScreenEvents = MutableSharedFlow<ScreenEvent>()
    val screenEvents = mutableScreenEvents.asSharedFlow()

    fun onAddRequestClicked() {
        viewModelScope.launch {
            mutableScreenEvents.emit(ShowRequestEditScreen(Constants.NO_ID))
        }
    }

    fun onItemClicked(itemId: Long) {
        viewModelScope.launch {
            mutableScreenEvents.emit(ShowRequestEditScreen(itemId))
        }
    }

    fun onItemEnabledStateChanged(itemId: Long, isEnabled: Boolean) {
        viewModelScope.launch {
            setRequestEnabledStateUseCase.invoke(itemId, isEnabled)
        }
    }

    fun onItemRemoveClicked(itemId: Long) {
        viewModelScope.launch {
            removeRequestUseCase.invoke(itemId)
        }
    }

    private fun createRequestListItem(item: RequestInfo): RequestListItem {
        return RequestListItem(
            id = item.id,
            request = item.requestParams.toString(),
            isChecked = item.isEnabled
        )
    }

    class ShowRequestEditScreen(val requestId: Long): ScreenEvent
}

