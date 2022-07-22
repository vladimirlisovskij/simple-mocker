package com.github.vladimirlisovskij.simple_mocker.ui.di

import com.github.vladimirlisovskij.simple_mocker.ui.di.modules.ViewModelModule
import com.github.vladimirlisovskij.simple_mocker.ui.request_editor.RequestEditorViewModel
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.RequestListViewModel
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