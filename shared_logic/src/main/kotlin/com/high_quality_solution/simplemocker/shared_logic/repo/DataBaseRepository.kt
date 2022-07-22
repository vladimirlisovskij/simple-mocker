package com.high_quality_solution.simplemocker.shared_logic.repo

import com.high_quality_solution.simplemocker.shared_logic.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared_logic.dto.RequestParams

interface DataBaseRepository {
    fun insertMockRequest(requestInfo: RequestInfo)
    fun removeMockRequestById(id: Long)
    fun getMockRequests(): List<RequestInfo>
    fun findRequest(params: RequestParams): RequestInfo?
}

