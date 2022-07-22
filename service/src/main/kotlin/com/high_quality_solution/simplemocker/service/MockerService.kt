package com.high_quality_solution.simplemocker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.high_quality_solution.simplemocker.service.di.DaggerServiceComponent
import com.high_quality_solution.simplemocker.service.di.ServiceDependencies
import com.high_quality_solution.simplemocker.shared.di.DaggerSharedProvidesComponent
import javax.inject.Inject

class MockerService : Service() {
    @Inject
    lateinit var mockerBinderImpl: MockerBinderImpl

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    override fun onBind(p0: Intent?): IBinder {
        return mockerBinderImpl
    }

    private fun injectDependencies() {
        val serviceDependencies = ServiceDependencies.instance
        val sharedProvides = DaggerSharedProvidesComponent
            .builder()
            .sharedDependencies(serviceDependencies)
            .build()

        DaggerServiceComponent
            .builder()
            .sharedProvidesComponent(sharedProvides)
            .serviceDependencies(serviceDependencies)
            .build()
            .inject(this)
    }
}
