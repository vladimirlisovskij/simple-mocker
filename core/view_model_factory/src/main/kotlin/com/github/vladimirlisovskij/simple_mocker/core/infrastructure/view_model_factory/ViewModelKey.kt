package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val viewModelClass: KClass<out ViewModel>)