package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactoryModule
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.api.RequestListApi
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules.FragmentModule
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules.ViewModelModule
import dagger.Component

@Component(
    dependencies = [RequestListImplDependencies::class],
    modules = [FragmentModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
)
internal interface RequestListImplApi: RequestListApi {
    @Component.Factory
    interface Factory {
        fun create(deps: RequestListImplDependencies): RequestListImplApi
    }
}