package com.high_quality_solution.simplemocker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MockerService : Service() {
    lateinit var mockerBinderImpl: MockerBinderImpl

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder {
        return mockerBinderImpl
    }
}
