package com.high_quality_solution.simplemocker.app

import android.app.Application
import com.high_quality_solution.simplemocker.app.di.AppScopeComponent
import com.high_quality_solution.simplemocker.app.di.ComponentScopeComponent
import com.high_quality_solution.simplemocker.app.di.DaggerAppScopeComponent
import com.high_quality_solution.simplemocker.app.di.DaggerComponentScopeComponent
import com.high_quality_solution.simplemocker.service.di.ServiceComponentDependencies
import com.high_quality_solution.simplemocker.ui.di.UIComponentDependencies

class MockerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        val appComponent = createAppScopeComponent()
        ServiceComponentDependencies.creator = { createComponentScopeComponent(appComponent) }
        UIComponentDependencies.creator = { createComponentScopeComponent(appComponent) }
    }

    private fun createAppScopeComponent(): AppScopeComponent {
        return DaggerAppScopeComponent.factory().create(this)
    }

    private fun createComponentScopeComponent(
        appScopeComponent: AppScopeComponent
    ): ComponentScopeComponent {
        return DaggerComponentScopeComponent
            .builder()
            .appScopeComponent(appScopeComponent)
            .build()
    }
}
