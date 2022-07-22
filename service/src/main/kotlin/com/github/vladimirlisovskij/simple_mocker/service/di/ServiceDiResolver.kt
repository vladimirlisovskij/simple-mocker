package com.github.vladimirlisovskij.simple_mocker.service.di

import kotlin.properties.Delegates

object ServiceDiResolver {
    var dependenciesCreator: () -> ServiceDiDependencies by Delegates.notNull()
    private var instance: ServiceComponent? = null

    fun getApi(): ServiceComponent {
        return instance
            ?: DaggerServiceComponent.factory()
                .create(dependenciesCreator.invoke())
                .also(::instance::set)
    }
}