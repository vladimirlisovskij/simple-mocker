package com.github.vladimirlisovskij.simple_mocker.shared.di

import com.github.vladimirlisovskij.simple_mocker.shared.usecase.*

interface SharedDiApi {
    fun getMockResponseUseCase(): GetMockResponseUseCase
    fun getRequestListUseCase(): GetRequestListUseCase
    fun createMockRequestUseCase(): CreateMockRequestUseCase
    fun getRequestByIdUseCase(): GetRequestByIdUseCase
    fun getUriForFileNameUseCase(): GetUriForFileNameUseCase
    fun getFileNameByUriUseCase(): GetFileNameByUriUseCase
    fun setRequestEnabledStateUseCase(): SetRequestEnabledStateUseCase
    fun removeRequestUseCase(): RemoveRequestUseCase
    fun updateRequestUseCase(): UpdateRequestUseCase
}
