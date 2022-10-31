package com.github.vladimirlisovskij.simple_mocker.common.component_holder

interface LazyComponentHolder<Deps : HolderDependencies, Api : HolderApi> {
    fun init(block: () -> Deps)

    @Throws(ComponentHolderNotInitializedException::class)
    fun getApi(): Api
}

interface HolderDependencies

interface HolderApi

class ComponentHolderNotInitializedException : Exception()
