package com.high_quality_solution.simplemocker.service.di.module

import com.high_quality_solution.simplemocker.service.GetMockResponseGateway
import com.high_quality_solution.simplemocker.service.MockerBinderImpl
import dagger.Module
import dagger.Provides

@Module
object BinderModule {
    @Provides
    fun provideMockerBinder(getMockResponseGateway: GetMockResponseGateway) = MockerBinderImpl(getMockResponseGateway)
}
