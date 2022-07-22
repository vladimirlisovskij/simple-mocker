package com.high_quality_solution.simplemocker.ui.di.modules

import android.content.Context
import com.high_quality_solution.simplemocker.shared.usecase.*
import com.high_quality_solution.simplemocker.ui.request_editor.RequestEditorViewModel
import com.high_quality_solution.simplemocker.ui.request_list.RequestListViewModel
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    fun provideRequestListViewModel(
        getRequestListUseCase: GetRequestListUseCase,
        setRequestEnabledStateUseCase: SetRequestEnabledStateUseCase,
        removeRequestUseCase: RemoveRequestUseCase
    ) = RequestListViewModel(
        getRequestListUseCase,
        setRequestEnabledStateUseCase,
        removeRequestUseCase
    )

    @Provides
    fun provideRequestEditorViewModelFactory(
        context: Context,
        createMockRequestUseCase: CreateMockRequestUseCase,
        getRequestByIdUseCase: GetRequestByIdUseCase,
        getUriForFileNameUseCase: GetUriForFileNameUseCase,
        getFileNameByUriUseCase: GetFileNameByUriUseCase
    ) = RequestEditorViewModel.Factory(
        context,
        createMockRequestUseCase,
        getRequestByIdUseCase,
        getUriForFileNameUseCase,
        getFileNameByUriUseCase
    )
}