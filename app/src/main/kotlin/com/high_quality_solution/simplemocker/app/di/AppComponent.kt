package com.high_quality_solution.simplemocker.app.di

import android.content.Context
import com.high_quality_solution.simplemocker.service.di.ServiceDependencies
import com.high_quality_solution.simplemocker.shared.di.SharedAppModules
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SharedAppModules::class]
)
interface AppComponent : ServiceDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}
