package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.state.ScreenState
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.view_model.BaseViewModel
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactoryDelegate

class RequestListViewModel: BaseViewModel<RequestListScreenState>() {
    override fun createInitialState(): RequestListScreenState {
        TODO("Not yet implemented")
    }
}

class RequestListViewModelFactory: ViewModelFactoryDelegate {
    override fun createViewModel(extras: CreationExtras): ViewModel {
        return RequestListViewModel()
    }
}

data class RequestListScreenState(
    val requests: List<Any>
): ScreenState
