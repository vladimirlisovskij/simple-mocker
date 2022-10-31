package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras

interface ViewModelFactoryDelegate {
    fun createViewModel(extras: CreationExtras): ViewModel
}
