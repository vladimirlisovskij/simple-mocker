package com.high_quality_solution.simplemocker.service.di.module

import com.high_quality_solution.simplemocker.service.GetMockResponseGateway
import com.high_quality_solution.simplemocker.shared.usecase.GetMockResponseUseCase
import dagger.Module
import dagger.Provides

@Module
object GateWayModule {
    @Provides
    fun provideGetMockResponseGateway(getMockResponseUseCase: GetMockResponseUseCase) =
        GetMockResponseGateway(getMockResponseUseCase)
}
