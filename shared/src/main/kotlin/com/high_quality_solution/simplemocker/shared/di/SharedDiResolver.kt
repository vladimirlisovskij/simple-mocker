package com.high_quality_solution.simplemocker.shared.di

import java.lang.ref.WeakReference
import kotlin.properties.Delegates

object SharedDiResolver {
    var dependenciesCreator: () -> SharedDiDependencies by Delegates.notNull()
    private var apiWeakInstance = WeakReference<SharedDiApi>(null)

    fun getApi(): SharedDiApi {
        synchronized(this) {
            return apiWeakInstance.get()
                ?: DaggerSharedApiComponent.factory()
                    .create(dependenciesCreator.invoke())
                    .also { apiWeakInstance = WeakReference(it) }
        }
    }
}

