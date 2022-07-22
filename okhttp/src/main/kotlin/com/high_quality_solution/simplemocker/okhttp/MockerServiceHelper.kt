package com.high_quality_solution.simplemocker.okhttp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.high_quality_solution.simplemocker.aidl.MockerBinder

object MockerServiceHelper {
    var mockerBinder: MockerBinder? = null
        private set

    private val serviceConnection = createServiceConnection()

    fun startService(context: Context) {
        context.bindService(
            createServiceStartupIntent(),
            serviceConnection,
            0
        )
    }

    fun stopService(context: Context) {
        context.unbindService(serviceConnection)
    }

    private fun createServiceStartupIntent() = Intent().apply {
        setClassName(SERVICE_PACKAGE, SERVICE_NAME)
    }

    private fun createServiceConnection() = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mockerBinder = MockerBinder.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mockerBinder = null
        }
    }

    private const val SERVICE_PACKAGE = "com.high_quality_solution.simplemocker.service"
    private const val SERVICE_NAME = "MockerService"
}
