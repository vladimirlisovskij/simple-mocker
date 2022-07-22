package com.high_quality_solution.simplemocker.shared.di

import com.high_quality_solution.simplemocker.shared.usecase.*

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
