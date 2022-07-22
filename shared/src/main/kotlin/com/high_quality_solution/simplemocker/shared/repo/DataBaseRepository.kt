package com.high_quality_solution.simplemocker.shared.repo

import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase

class DataBaseRepository(
    private val database: MockRequestsDatabase
) {
    fun insertMockRequest(requestInfo: RequestInfo) {
        database.requestQueries.insert(
            id = requestInfo.id,
            path = requestInfo.path,
            params = requestInfo.params,
            host = requestInfo.host,
            bodyFileName = requestInfo.bodyFileName
        )
    }

    fun removeMockRequestById(id: Long) {
        database.requestQueries.delete(id)
    }

    fun getMockRequests(): List<RequestInfo> {
        return database.requestQueries.getAll(::createRequestInfo).executeAsList()
    }

    fun findRequest(params: RequestParams): RequestInfo? {
        return database.requestQueries.findRequest(
            path = params.path,
            host = params.host,
            params = params.params,
            mapper = ::createRequestInfo
        ).executeAsOneOrNull()
    }

    private fun createRequestInfo(
        id: Long,
        path: String,
        params: String?,
        host: String?,
        bodyFileName: String
    ) = RequestInfo(
        id = id,
        path = path,
        params = params,
        host = host,
        bodyFileName = bodyFileName
    )
}
