package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di

import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container.ApiContainer
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.api.RequestListApi

object RequestListApiContainer: ApiContainer<RequestListApi, RequestListImplDependencies>() {
    override fun createApi(dependencies: RequestListImplDependencies): RequestListApi {
        return DaggerRequestListImplApi.factory().create(dependencies)
    }
}