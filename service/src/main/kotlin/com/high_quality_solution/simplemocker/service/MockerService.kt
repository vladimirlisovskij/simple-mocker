package com.high_quality_solution.simplemocker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.high_quality_solution.simplemocker.service.di.DaggerServiceComponent
import com.high_quality_solution.simplemocker.service.di.ServiceComponent
import com.high_quality_solution.simplemocker.service.di.ServiceComponentDependencies
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
        ServiceComponent.create().inject(this)
    }
}
