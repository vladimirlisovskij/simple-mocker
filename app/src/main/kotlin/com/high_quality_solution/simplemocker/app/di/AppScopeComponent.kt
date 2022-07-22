package com.high_quality_solution.simplemocker.app.di

import android.content.Context
import com.high_quality_solution.simplemocker.shared.di.SharedAppDependencies
import com.high_quality_solution.simplemocker.shared.di.SharedAppModules
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SharedAppModules::class]
)
interface AppScopeComponent : SharedAppDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppScopeComponent
    }
}

