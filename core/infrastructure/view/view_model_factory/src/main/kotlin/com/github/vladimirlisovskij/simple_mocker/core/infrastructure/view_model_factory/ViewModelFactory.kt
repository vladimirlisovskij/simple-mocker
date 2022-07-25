package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import javax.inject.Provider

class ViewModelFactory(
    private val delegateProviders: Map<Class<out ViewModel>, Provider<ViewModelFactoryDelegate>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val delegate = delegateProviders[modelClass]?.get() ?: throw Exception("Unknown ViewModel class")
        return delegate.createViewModel(extras) as T
    }
}
