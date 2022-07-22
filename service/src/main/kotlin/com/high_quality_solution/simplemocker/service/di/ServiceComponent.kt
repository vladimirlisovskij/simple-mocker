package com.high_quality_solution.simplemocker.service.di

import com.high_quality_solution.simplemocker.service.MockerService
import com.high_quality_solution.simplemocker.service.di.module.BinderModule
import com.high_quality_solution.simplemocker.service.di.module.GateWayModule
import dagger.Component

@Component(
    dependencies = [ServiceComponentDependencies::class],
    modules = [BinderModule::class, GateWayModule::class]
)
interface ServiceComponent {
    fun inject(service: MockerService)

    companion object {
        fun create(): ServiceComponent {
            return DaggerServiceComponent
                .builder()
                .serviceComponentDependencies(ServiceComponentDependencies.creator.invoke())
                .build()
        }
    }
}
