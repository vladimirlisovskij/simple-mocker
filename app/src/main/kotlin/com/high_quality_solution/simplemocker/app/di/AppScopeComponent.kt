package com.high_quality_solution.simplemocker.app.di

import android.content.Context
import com.high_quality_solution.simplemocker.shared.di.SharedAppDependencies
import com.high_quality_solution.simplemocker.shared.di.SharedAppModules
import com.high_quality_solution.simplemocker.ui.di.UIAppDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [SharedAppModules::class]
)
interface AppScopeComponent : SharedAppDependencies, UIAppDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppScopeComponent
    }
}

