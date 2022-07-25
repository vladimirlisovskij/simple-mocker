package com.github.vladimirlisovskij.simple_mocker.feature.request_list.api

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container.ModuleDiApi
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.BaseFragment

interface RequestListApi: ModuleDiApi {
    val fragment: BaseFragment
}
