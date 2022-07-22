package com.high_quality_solution.simplemocker.ui.di

import com.high_quality_solution.simplemocker.shared.usecase.*
import kotlin.properties.Delegates

interface UIComponentDependencies {
    fun getRequestListUseCase(): GetRequestListUseCase
    fun createMockRequestUseCase(): CreateMockRequestUseCase
    fun getRequestByIdUseCase(): GetRequestByIdUseCase
    fun getUriForFileNameUseCase(): GetUriForFileNameUseCase
    fun getFileNameByUriUseCase(): GetFileNameByUriUseCase
    fun setRequestEnabledStateUseCase(): SetRequestEnabledStateUseCase

    companion object {
        var creator: () -> UIComponentDependencies by Delegates.notNull()
    }
}

