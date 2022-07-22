package com.github.vladimirlisovskij.simple_mocker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.github.vladimirlisovskij.simple_mocker.service.di.ServiceDiResolver
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
        ServiceDiResolver.getApi().inject(this)
    }
}
