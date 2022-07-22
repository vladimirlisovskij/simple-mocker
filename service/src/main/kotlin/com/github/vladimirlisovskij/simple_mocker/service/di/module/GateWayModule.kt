package com.github.vladimirlisovskij.simple_mocker.service.di.module

import com.github.vladimirlisovskij.simple_mocker.service.GetMockResponseGateway
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.GetMockResponseUseCase
import dagger.Module
import dagger.Provides

@Module
object GateWayModule {
    @Provides
    fun provideGetMockResponseGateway(getMockResponseUseCase: GetMockResponseUseCase) =
        GetMockResponseGateway(getMockResponseUseCase)
}
