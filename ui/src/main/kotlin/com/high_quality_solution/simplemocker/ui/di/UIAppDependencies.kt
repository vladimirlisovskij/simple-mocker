package com.high_quality_solution.simplemocker.ui.di

import android.content.Context
import kotlin.properties.Delegates

interface UIAppDependencies {
    fun context(): Context

    companion object {
        var creator: () -> UIAppDependencies by Delegates.notNull()
    }
}