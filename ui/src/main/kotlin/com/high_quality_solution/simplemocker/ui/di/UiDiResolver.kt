package com.high_quality_solution.simplemocker.ui.di

import kotlin.properties.Delegates

object UiDiResolver {
    var dependenciesCreator: () -> UiDiDependencies by Delegates.notNull()
    private var instance: UIComponent? = null

    fun getApi(): UIComponent {
        return instance
            ?: DaggerUIComponent.factory()
                .create(dependenciesCreator.invoke())
                .also(::instance::set)
    }
}