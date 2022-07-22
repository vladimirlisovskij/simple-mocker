package com.github.vladimirlisovskij.simple_mocker.service.di.module

import com.github.vladimirlisovskij.simple_mocker.service.GetMockResponseGateway
import com.github.vladimirlisovskij.simple_mocker.service.MockerBinderImpl
import dagger.Module
import dagger.Provides

@Module
object BinderModule {
    @Provides
    fun provideMockerBinder(getMockResponseGateway: GetMockResponseGateway) = MockerBinderImpl(getMockResponseGateway)
}
