package com.high_quality_solution.simplemocker.ui.di

import com.high_quality_solution.simplemocker.ui.di.modules.ViewModelModule
import com.high_quality_solution.simplemocker.ui.request_editor.RequestEditorViewModel
import com.high_quality_solution.simplemocker.ui.request_list.RequestListViewModel
import dagger.Component
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@Component(
    dependencies = [UIComponentDependencies::class],
    modules = [ViewModelModule::class]
)
interface UIComponent {
    fun requestListViewModel(): RequestListViewModel
    fun requestEditorViewModelFactory(): RequestEditorViewModel.Factory

    companion object {
        fun create(): UIComponent {
            return DaggerUIComponent
                .builder()
                .uIComponentDependencies(UIComponentDependencies.creator.invoke())
                .build()
        }
    }
}