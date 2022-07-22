package com.github.vladimirlisovskij.simple_mocker.service.di

import com.github.vladimirlisovskij.simple_mocker.service.MockerService
import com.github.vladimirlisovskij.simple_mocker.service.di.module.BinderModule
import com.github.vladimirlisovskij.simple_mocker.service.di.module.GateWayModule
import dagger.Component

@Component(
    dependencies = [ServiceDiDependencies::class],
    modules = [BinderModule::class, GateWayModule::class]
)
interface ServiceComponent {
    fun inject(service: MockerService)

    @Component.Factory
    interface Factory {
        fun create(
            serviceDiDependencies: ServiceDiDependencies
        ): ServiceComponent
    }
}

