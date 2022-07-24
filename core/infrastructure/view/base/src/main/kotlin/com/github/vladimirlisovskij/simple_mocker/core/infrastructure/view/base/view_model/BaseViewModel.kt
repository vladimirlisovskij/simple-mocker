package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.view_model

import androidx.lifecycle.ViewModel
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.event.ScreenEvent
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.state.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State: ScreenState>: ViewModel() {
    protected val mutableScreenState by lazy { MutableStateFlow(createInitialState()) }
    val screenState by lazy { mutableScreenState.asStateFlow() }

    protected val mutableScreenEvents = MutableSharedFlow<ScreenEvent>()
    val screenEvents = mutableScreenEvents.asSharedFlow()

    protected abstract fun createInitialState(): State
}