package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelKey
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model.RequestListViewModel
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model.RequestListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
internal class ViewModelModule {
    @Provides
    @ViewModelKey(RequestListViewModel::class)
    fun provideViewModelFactory() = RequestListViewModelFactory()
}