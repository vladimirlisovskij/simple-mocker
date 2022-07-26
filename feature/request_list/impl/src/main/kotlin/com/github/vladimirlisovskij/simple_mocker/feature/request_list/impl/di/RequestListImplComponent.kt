package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.navigation_factory.NavigationFactory
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactory
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactoryModule
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules.FragmentModule
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules.ViewModelModule
import dagger.Component

@Component(
    dependencies = [RequestListImplDependencies::class],
    modules = [FragmentModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
)
internal interface RequestListImplComponent {

    val navigationFactory: NavigationFactory

    val viewModelFactory: ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: RequestListImplDependencies): RequestListImplComponent
    }
}