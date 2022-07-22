package com.high_quality_solution.simplemocker.ui.di.modules

import com.high_quality_solution.simplemocker.shared.usecase.GetRequestListUseCase
import com.high_quality_solution.simplemocker.ui.request_editor.RequestEditorViewModel
import com.high_quality_solution.simplemocker.ui.request_list.RequestListViewModel
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    fun provideRequestListViewModel(
        getRequestListUseCase: GetRequestListUseCase
    ) = RequestListViewModel(
        getRequestListUseCase
    )

    @Provides
    fun provideRequestEditorViewModelFactory() = RequestEditorViewModel.Factory()
}