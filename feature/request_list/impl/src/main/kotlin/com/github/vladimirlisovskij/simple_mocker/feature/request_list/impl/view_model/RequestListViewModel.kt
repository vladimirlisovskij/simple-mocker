package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.view_model.BaseViewModel

class RequestListViewModel: BaseViewModel<RequestListScreenState>() {
    override fun createInitialState(): RequestListScreenState {
        return RequestListScreenState(emptyList())
    }
}

