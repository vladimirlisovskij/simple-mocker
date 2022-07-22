package com.high_quality_solution.simplemocker.shared.di

import com.high_quality_solution.simplemocker.shared.di.module.UseCaseModule
import com.high_quality_solution.simplemocker.shared.usecase.GetMockResponseUseCase
import dagger.Component

@Component(
    dependencies = [SharedDependencies::class],
    modules = [UseCaseModule::class]
)
interface SharedProvidesComponent {
    fun getMockResponseUseCase(): GetMockResponseUseCase
}
