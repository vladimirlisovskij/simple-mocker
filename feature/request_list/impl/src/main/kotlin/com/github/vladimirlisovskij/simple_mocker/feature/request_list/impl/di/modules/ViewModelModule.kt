package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactoryDelegate
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelKey
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model.RequestListViewModel
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model.RequestListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(RequestListViewModel::class)
    fun provideViewModelFactory() = RequestListViewModelFactory() as ViewModelFactoryDelegate
}