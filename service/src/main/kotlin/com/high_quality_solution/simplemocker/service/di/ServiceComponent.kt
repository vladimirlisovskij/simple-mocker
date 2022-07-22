package com.high_quality_solution.simplemocker.service.di

import com.high_quality_solution.simplemocker.service.MockerService
import com.high_quality_solution.simplemocker.service.di.module.BinderModule
import com.high_quality_solution.simplemocker.service.di.module.GateWayModule
import com.high_quality_solution.simplemocker.shared.di.SharedProvidesComponent
import dagger.Component

@Component(
    dependencies = [SharedProvidesComponent::class, ServiceDependencies::class],
    modules = [BinderModule::class, GateWayModule::class]
)
interface ServiceComponent {
    fun inject(service: MockerService)
}
