package com.high_quality_solution.simplemocker.shared.repo

import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestParams
import com.high_quality_solution.simplemocker.shared.dto.ResponseInfo
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DataBaseRepository(
    private val database: MockRequestsDatabase
) {
    suspend fun insertMockRequest(requestInfo: RequestInfo) {
        withContext(Dispatchers.IO) {
            database.requestQueries.insert(
                path = requestInfo.requestParams.path,
                params = requestInfo.requestParams.params,
                host = requestInfo.requestParams.host,
                bodyFileName = requestInfo.bodyFileName
            )
        }
    }

    suspend fun getRequestById(id: Long): RequestInfo {
        return withContext(Dispatchers.IO) {
            database.requestQueries.getById(
                id = id,
                mapper = ::createRequestInfo
            ).executeAsOne()
        }
    }

    suspend fun removeMockRequestById(id: Long) {
        withContext(Dispatchers.IO) {
            database.requestQueries.deleteById(id)
        }
    }

    fun getMockRequests(): Flow<List<RequestInfo>> {
        return database.requestQueries
            .getAll(::createRequestInfo)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun findRequestResponse(params: RequestParams): ResponseInfo? {
        return database.requestQueries.findRequestResponse(
            path = params.path,
//            host = params.host,
//            params = params.params,
        )
            .executeAsOneOrNull()
            ?.let(::createResponseInfo)
    }

    suspend fun setRequestEnabledState(id: Long, isEnabled: Boolean) {
        withContext(Dispatchers.IO) {
            database.requestQueries.setEnableState(
                id = id,
                isEnabled = if (isEnabled) REQUEST_ENABLED else REQUEST_DISABLED
            )
        }
    }

    private fun createRequestInfo(
        id: Long,
        path: String,
        params: String?,
        host: String?,
        bodyFileName: String,
        isEnabled: Long
    ): RequestInfo {
        val requestParams = RequestParams(
            host = host,
            path = path,
            params = params
        )

        return RequestInfo(
            id = id,
            requestParams = requestParams,
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
