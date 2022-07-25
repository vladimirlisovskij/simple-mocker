package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container.ModuleDiDependencies
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.navigation_factory.NavigationFactory

interface RequestListImplDependencies: ModuleDiDependencies {
    val navigationFactory: NavigationFactory
}