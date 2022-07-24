package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container

import java.lang.ref.WeakReference
import kotlin.properties.Delegates

abstract class ApiContainer<Api : ModuleDiApi, Deps : ModuleDiDependencies> {
    private var instance: WeakReference<Api> = WeakReference(null)
    var dependencyCreator: () -> Deps by Delegates.notNull()

    fun get(): Api {
        return synchronized(this) {
            instance.get()
                ?: createApi(dependencyCreator.invoke())
                    .also { instance = WeakReference(it) }
        }
    }

    abstract fun createApi(dependencies: Deps): Api
}