package com.github.vladimirlisovskij.simple_mocker.ui.di

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.*

interface UiDiDependencies {
    fun context(): Context
    fun getRequestListUseCase(): GetRequestListUseCase
    fun createMockRequestUseCase(): CreateMockRequestUseCase
    fun getRequestByIdUseCase(): GetRequestByIdUseCase
    fun getUriForFileNameUseCase(): GetUriForFileNameUseCase
    fun getFileNameByUriUseCase(): GetFileNameByUriUseCase
    fun setRequestEnabledStateUseCase(): SetRequestEnabledStateUseCase
    fun removeRequestUseCase(): RemoveRequestUseCase
    fun updateRequestUseCase(): UpdateRequestUseCase
}


