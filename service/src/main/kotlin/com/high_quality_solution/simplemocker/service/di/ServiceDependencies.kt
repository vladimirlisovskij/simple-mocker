package com.high_quality_solution.simplemocker.service.di

import com.high_quality_solution.simplemocker.shared.di.SharedDependencies
import kotlin.properties.Delegates

interface ServiceDependencies : SharedDependencies {
    companion object {
        var instance by Delegates.notNull<ServiceDependencies>()
    }
}
