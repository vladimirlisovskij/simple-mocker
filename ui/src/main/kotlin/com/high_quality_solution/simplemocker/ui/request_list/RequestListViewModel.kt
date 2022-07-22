package com.high_quality_solution.simplemocker.ui.request_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.usecase.GetRequestListUseCase
import com.high_quality_solution.simplemocker.shared.usecase.RemoveRequestUseCase
import com.high_quality_solution.simplemocker.shared.usecase.SetRequestEnabledStateUseCase
import com.high_quality_solution.simplemocker.ui.base.ScreenEvent
import com.high_quality_solution.simplemocker.ui.request_list.adapter.RequestListItem
import com.high_quality_solution.simplemocker.ui.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RequestListViewModel(
    private val getRequestListUseCase: GetRequestListUseCase,
    private val setRequestEnabledStateUseCase: SetRequestEnabledStateUseCase,
    private val removeRequestUseCase: RemoveRequestUseCase
) : ViewModel() {
    val requestsList = getRequestListUseCase
        .invoke()
        .map {
            Log.d("ITEMS", "items ${it.size} $it")
            it.map(::createRequestListItem)
        }
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

