package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container

import java.lang.ref.WeakReference
import kotlin.properties.Delegates

abstract class DependencyContainer<T> {
    private var instance: WeakReference<T> = WeakReference(null)
    var dependencyCreator: () -> T by Delegates.notNull()

    fun get(): T {
        return synchronized(this) {
            instance.get() ?: dependencyCreator.invoke().also { instance = WeakReference(it) }
        }
    }
}