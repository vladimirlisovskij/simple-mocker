package com.high_quality_solution.simplemocker.service

import com.high_quality_solution.simplemocker.aidl.MockRequestAidl
import com.high_quality_solution.simplemocker.aidl.MockResponseAidl
import com.high_quality_solution.simplemocker.shared.dto.MockRequest
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.usecase.GetMockResponseUseCase
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
