package com.github.vladimirlisovskij.simple_mocker.ui.di.modules

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.*
import com.github.vladimirlisovskij.simple_mocker.ui.request_editor.RequestEditorViewModel
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.RequestListViewModel
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
        getFileNameByUriUseCase: GetFileNameByUriUseCase,
        updateRequestUseCase: UpdateRequestUseCase,
    ) = RequestEditorViewModel.Factory(
        context,
        createMockRequestUseCase,
        getRequestByIdUseCase,
        getUriForFileNameUseCase,
        getFileNameByUriUseCase,
        updateRequestUseCase
    )
}