package com.github.vladimirlisovskij.simple_mocker.shared.di

import com.github.vladimirlisovskij.simple_mocker.shared.di.module.DataBaseModule
import com.github.vladimirlisovskij.simple_mocker.shared.di.module.RepositoryModule
import com.github.vladimirlisovskij.simple_mocker.shared.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [SharedDiDependencies::class],
    modules = [UseCaseModule::class, RepositoryModule::class, DataBaseModule::class]
)
interface SharedApiComponent: SharedDiApi {

    @Component.Factory
    interface Factory {
        fun create(
            sharedDiDependencies: SharedDiDependencies
        ): SharedApiComponent
    }
}