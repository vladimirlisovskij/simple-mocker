package com.github.vladimirlisovskij.simple_mocker.service

import com.github.vladimirlisovskij.simple_mocker.aidl.MockRequestAidl
import com.github.vladimirlisovskij.simple_mocker.aidl.MockResponseAidl
import com.github.vladimirlisovskij.simple_mocker.shared.dto.MockRequest
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestParams
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.GetMockResponseUseCase
import javax.inject.Inject

class GetMockResponseGateway @Inject constructor(
    private val getMockResponseUseCase: GetMockResponseUseCase
) {
    operator fun invoke(request: MockRequestAidl): MockResponseAidl? {
        return MockRequest(
            request.appPackage,
            RequestParams(
                path = request.path,
                params = request.params,
                host = request.host
            )
        )
            .let(getMockResponseUseCase::invoke)
            ?.let { MockResponseAidl(it.bodyUrl) }
    }
}
