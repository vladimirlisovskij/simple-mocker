package com.high_quality_solution.simplemocker.service

import com.high_quality_solution.simplemocker.aidl.MockRequestAidl
import com.high_quality_solution.simplemocker.aidl.MockResponseAidl
import com.high_quality_solution.simplemocker.shared_logic.usecase.GetMockResponseUseCase
import com.high_quality_solution.simplemocker.shared_logic.dto.MockRequest
import com.high_quality_solution.simplemocker.shared_logic.dto.RequestParams

class GetMockResponseGateway(
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