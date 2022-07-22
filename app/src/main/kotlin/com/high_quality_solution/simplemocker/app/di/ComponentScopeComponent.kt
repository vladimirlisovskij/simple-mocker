package com.high_quality_solution.simplemocker.app.di

import com.high_quality_solution.simplemocker.service.di.ServiceComponentDependencies
import com.high_quality_solution.simplemocker.shared.di.SharedComponentModules
import com.high_quality_solution.simplemocker.ui.di.UIComponentDependencies
import dagger.Component

@Component(
    dependencies = [AppScopeComponent::class],
    modules = [SharedComponentModules::class]
)
interface ComponentScopeComponent: ServiceComponentDependencies, UIComponentDependencies