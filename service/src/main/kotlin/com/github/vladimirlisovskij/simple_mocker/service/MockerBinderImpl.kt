package com.github.vladimirlisovskij.simple_mocker.service

import com.github.vladimirlisovskij.simple_mocker.aidl.MockRequestAidl
import com.github.vladimirlisovskij.simple_mocker.aidl.MockResponseAidl
import com.github.vladimirlisovskij.simple_mocker.aidl.MockerBinder

class MockerBinderImpl(
    private val getMockResponseGateway: GetMockResponseGateway
) : MockerBinder.Stub() {
    override fun getMockRequestParams(request: MockRequestAidl): MockResponseAidl? {
        return getMockResponseGateway.invoke(request)
    }
}
