package com.high_quality_solution.simplemocker.service.di

import com.high_quality_solution.simplemocker.shared.usecase.GetMockResponseUseCase
import kotlin.properties.Delegates

interface ServiceComponentDependencies {
    fun getMockResponseUseCase(): GetMockResponseUseCase

    companion object {
        var creator: () -> ServiceComponentDependencies by Delegates.notNull()
    }
}
