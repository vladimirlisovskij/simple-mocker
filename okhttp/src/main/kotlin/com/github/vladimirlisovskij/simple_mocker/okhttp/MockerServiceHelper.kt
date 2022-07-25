package com.github.vladimirlisovskij.simple_mocker.okhttp

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.github.vladimirlisovskij.simple_mocker.core.aidl.MockerBinder

class MockerServiceHelper private constructor(
    private val context: Context
) : Application.ActivityLifecycleCallbacks, ServiceConnection {
    private var runningActivities = 0

    override fun onActivityStarted(activity: Activity) {
        if (runningActivities == 0) {
            context.bindService(
                createServiceStartupIntent(),
                this,
                Context.BIND_AUTO_CREATE
            )
        }

        runningActivities++
    }

    override fun onActivityStopped(activity: Activity) {
        runningActivities--
        if (runningActivities == 0) {
            context.unbindService(this)
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mockerBinder = MockerBinder.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mockerBinder = null
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    private fun createServiceStartupIntent() = Intent().apply {
        setClassName(SERVICE_PACKAGE, SERVICE_NAME)
    }

    companion object {
        private const val SERVICE_PACKAGE = "com.github.vladimirlisovskij.simple_mocker"
        private const val SERVICE_NAME = "com.github.vladimirlisovskij.simple_mocker.service.MockerService"

        var mockerBinder: MockerBinder? = null
            private set

        fun registerMocker(application: Application) {
            val instance = MockerServiceHelper(application)
            application.registerActivityLifecycleCallbacks(instance)
        }
    }
}
