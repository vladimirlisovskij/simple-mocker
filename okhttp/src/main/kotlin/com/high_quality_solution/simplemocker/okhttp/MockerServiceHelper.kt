package com.high_quality_solution.simplemocker.okhttp

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.high_quality_solution.simplemocker.aidl.MockerBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MockerServiceHelper private constructor(
    private val context: Context
) : Application.ActivityLifecycleCallbacks, ServiceConnection {
    private var runningActivities = 0

    override fun onActivityStarted(activity: Activity) {
        Log.d("MOCKERTAG", "activity started $runningActivities")
        if (runningActivities == 0) {
            context.bindService(
                createServiceStartupIntent(),
                this,
                Context.BIND_AUTO_CREATE
            ).also {
                Log.d("MOCKERTAG", "BIND $it")
            }
        }

        runningActivities++
    }

    override fun onActivityStopped(activity: Activity) {
        Log.d("MOCKERTAG", "activity stoped $runningActivities")
        runningActivities--
        if (runningActivities == 0) {
            Log.d("MOCKERTAG", "UNBIND")
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
        private const val SERVICE_PACKAGE = "com.high_quality_solution.simplemocker"
        private const val SERVICE_NAME = "com.high_quality_solution.simplemocker.service.MockerService"

        var mockerBinder: MockerBinder? = null
            private set

        fun registerMocker(application: Application) {
            val instance = MockerServiceHelper(application)
            application.registerActivityLifecycleCallbacks(instance)
        }
    }
}
