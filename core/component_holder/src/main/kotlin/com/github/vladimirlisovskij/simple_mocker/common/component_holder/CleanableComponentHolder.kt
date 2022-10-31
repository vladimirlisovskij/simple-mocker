package com.github.vladimirlisovskij.simple_mocker.common.component_holder

abstract class CleanableComponentHolder<Deps : HolderDependencies, Api : HolderApi> : LazyComponentHolder<Deps, Api> {
    private var instance: Api? = null
    private var dependenciesProvider: (() -> Deps)? = null
    private val dependencies get() = dependenciesProvider?.invoke() ?: throw ComponentHolderNotInitializedException()

    override fun init(block: () -> Deps) {
        dependenciesProvider = block
    }

    override fun getApi(): Api {
        return synchronized(this) { instance ?: createApi(dependencies).also(this::instance::set) }
    }

    fun clear() {
        instance = null
    }

    abstract fun createApi(dependencies: Deps): Api
}