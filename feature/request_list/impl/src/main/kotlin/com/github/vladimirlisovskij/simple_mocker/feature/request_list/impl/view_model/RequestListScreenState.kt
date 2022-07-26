package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.state.ScreenState

data class RequestListScreenState(
    val requests: List<Any>
): ScreenState