package com.high_quality_solution.simplemocker.shared.di

import com.high_quality_solution.simplemocker.shared.di.module.DataBaseModule
import com.high_quality_solution.simplemocker.shared.di.module.RepositoryModule
import com.high_quality_solution.simplemocker.shared.di.module.UseCaseModule
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