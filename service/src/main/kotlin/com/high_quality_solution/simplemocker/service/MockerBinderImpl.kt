package com.high_quality_solution.simplemocker.service

import com.high_quality_solution.simplemocker.aidl.MockRequestAidl
import com.high_quality_solution.simplemocker.aidl.MockResponseAidl
import com.high_quality_solution.simplemocker.aidl.MockerBinder

class MockerBinderImpl(
    private val getMockResponseGateway: GetMockResponseGateway
) : MockerBinder.Stub() {
    override fun getMockRequestParams(request: MockRequestAidl): MockResponseAidl? {
        return getMockResponseGateway.invoke(request)
    }
}
