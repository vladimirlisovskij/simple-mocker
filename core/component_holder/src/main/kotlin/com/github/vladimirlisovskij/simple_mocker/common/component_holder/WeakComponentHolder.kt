package com.github.vladimirlisovskij.simple_mocker.common.component_holder

import java.lang.ref.WeakReference

abstract class WeakComponentHolder<Deps : HolderDependencies, Api : HolderApi> : LazyComponentHolder<Deps, Api> {
    private var instance = WeakReference<Api>(null)
    private var dependenciesProvider: (() -> Deps)? = null
    private val dependencies get() = dependenciesProvider?.invoke() ?: throw ComponentHolderNotInitializedException()

    override fun init(block: () -> Deps) {
        dependenciesProvider = block
    }

    override fun getApi(): Api {
        return synchronized(this) { instance.get() ?: createApi(dependencies).also { instance = WeakReference(it) } }
    }

    protected abstract fun createApi(dependencies: Deps): Api
}
