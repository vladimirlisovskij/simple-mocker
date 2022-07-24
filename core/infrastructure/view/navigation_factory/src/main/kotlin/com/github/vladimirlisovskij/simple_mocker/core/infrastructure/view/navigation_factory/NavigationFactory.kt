package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.navigation_factory

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.BaseFragment

fun interface NavigationFactory {
    fun navigate(from: BaseFragment, event: NavigationEvent)
}