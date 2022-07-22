package com.high_quality_solution.simplemocker.ui.di

import com.high_quality_solution.simplemocker.ui.di.modules.ViewModelModule
import com.high_quality_solution.simplemocker.ui.request_editor.RequestEditorViewModel
import com.high_quality_solution.simplemocker.ui.request_list.RequestListViewModel
import dagger.Component

@Component(
    dependencies = [UiDiDependencies::class],
    modules = [ViewModelModule::class]
)
interface UIComponent {
    fun requestListViewModel(): RequestListViewModel
    fun requestEditorViewModelFactory(): RequestEditorViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(
            uiDiDependencies: UiDiDependencies
        ): UIComponent
    }
}