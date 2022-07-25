package com.github.vladimirlisovskij.simple_mocker.feature.request_list.api

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.navigation_factory.NavigationEvent

sealed interface RequestListNavigation: NavigationEvent {
    object OpenRequestEditor: RequestListNavigation
}