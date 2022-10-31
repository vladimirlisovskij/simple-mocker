package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {
    @Provides
    fun provideFactory(
        factories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelFactoryDelegate>>
    ) = ViewModelFactory(factories)
}