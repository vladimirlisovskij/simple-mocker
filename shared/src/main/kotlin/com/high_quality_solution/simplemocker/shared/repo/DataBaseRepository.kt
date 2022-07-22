package com.high_quality_solution.simplemocker.shared.repo

import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.dto.ResponseInfo
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

class DataBaseRepository(
    private val database: MockRequestsDatabase
) {
    fun insertMockRequest(requestInfo: RequestInfo) {
        database.requestQueries.insert(
            id = requestInfo.id,
            path = requestInfo.requestParams.path,
            params = requestInfo.requestParams.params,
            host = requestInfo.requestParams.host,
            bodyFileName = requestInfo.bodyFileName
        )
    }

    fun removeMockRequestById(id: Long) {
        database.requestQueries.delete(id)
    }

    fun getMockRequests(): Flow<List<RequestInfo>> {
        return database.requestQueries
            .getAll(::createRequestInfo)
            .asFlow()
            .mapToList()
    }

    fun findRequestResponse(params: RequestParams): ResponseInfo? {
        return database.requestQueries.findRequestResponse(
            path = params.path,
            host = params.host,
            params = params.params,
        )
            .executeAsOneOrNull()
            ?.let(::createResponseInfo)
    }

    private fun createRequestInfo(
        id: Long,
        path: String,
        params: String?,
        host: String?,
        bodyFileName: String,
        isEnabled: Long
    ): RequestInfo {
        val params = RequestParams(
            host = host,
            path = path,
            params = params
        )

        return RequestInfo(
            id = id,
            requestParams = params,
            bodyFileName = bodyFileName,
            isEnabled = isEnabled == REQUEST_ENABLED
        )
    }

    private fun createResponseInfo(
        bodyFileName: String
    ) = ResponseInfo(
        bodyFileName = bodyFileName
    )

    companion object {
        private const val REQUEST_ENABLED = 1L
        private const val REQUEST_DISABLED = 0L
    }
}
