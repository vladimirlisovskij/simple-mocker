package com.high_quality_solution.simplemocker.shared.di

import com.high_quality_solution.simplemocker.shared.di.module.UseCaseModule
import dagger.Module

@Module(includes = [UseCaseModule::class])
object SharedComponentModules