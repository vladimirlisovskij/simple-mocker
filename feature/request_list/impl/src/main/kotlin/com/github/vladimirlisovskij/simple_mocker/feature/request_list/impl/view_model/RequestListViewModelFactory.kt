package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactoryDelegate

class RequestListViewModelFactory: ViewModelFactoryDelegate {
    override fun createViewModel(extras: CreationExtras): ViewModel {
        return RequestListViewModel()
    }
}