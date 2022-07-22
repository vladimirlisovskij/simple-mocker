package com.high_quality_solution.simplemocker.shared.di

import com.high_quality_solution.simplemocker.shared.di.module.DataBaseModule
import com.high_quality_solution.simplemocker.shared.di.module.RepositoryModule
import dagger.Module

@Module(includes = [RepositoryModule::class, DataBaseModule::class])
object SharedAppModules

