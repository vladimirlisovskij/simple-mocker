package com.high_quality_solution.simplemocker.app

import android.app.Application
import com.high_quality_solution.simplemocker.service.di.ServiceDependencies

class MockerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        val appComponent = DaggerAppComponent.factory().create(this)
        ServiceDependencies.instance = appComponent
    }
}
