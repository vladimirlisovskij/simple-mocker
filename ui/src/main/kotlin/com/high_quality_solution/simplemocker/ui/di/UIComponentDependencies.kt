package com.high_quality_solution.simplemocker.ui.di

import com.high_quality_solution.simplemocker.shared.usecase.GetRequestListUseCase
import kotlin.properties.Delegates

interface UIComponentDependencies {
    fun getRequestList(): GetRequestListUseCase

    companion object {
        var creator: () -> UIComponentDependencies by Delegates.notNull()
    }
}