package com.high_quality_solution.simplemocker.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

object Ext {
    inline fun<reified VM: ViewModel> Fragment.simpleViewModels(crossinline creator: () -> ViewModel): Lazy<VM> {
        return viewModels(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return creator.invoke() as T
                    }
                }
            }
        )
    }
}