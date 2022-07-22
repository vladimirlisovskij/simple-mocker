package com.github.vladimirlisovskij.simple_mocker.shared.repo_impl

import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestParams
import com.github.vladimirlisovskij.simple_mocker.shared.dto.ResponseInfo
import com.github.vladimirlisovskij.simple_mocker.shared.mock_requests_database.MockRequestsDatabase
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DataBaseRepositoryImpl(
    private val database: MockRequestsDatabase
) : DataBaseRepository {
    override suspend fun insertMockRequest(requestInfo: RequestInfo) {
        withContext(Dispatchers.IO) {
            database.requestQueries.insert(
                path = requestInfo.requestParams.path,
                params = requestInfo.requestParams.params,
                host = requestInfo.requestParams.host,
                bodyFileName = requestInfo.bodyFileName
            )
        }
    }

    override suspend fun getRequestById(id: Long): RequestInfo {
        return withContext(Dispatchers.IO) {
            database.requestQueries.getById(
                id = id,
                mapper = ::createRequestInfo
            ).executeAsOne()
        }
    }

    override suspend fun removeMockRequestById(id: Long) {
        withContext(Dispatchers.IO) {
            database.requestQueries.deleteById(id)
        }
    }

    override fun getMockRequests(): Flow<List<RequestInfo>> {
        return database.requestQueries
            .getAll(::createRequestInfo)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override fun findRequestResponse(params: RequestParams): ResponseInfo? {
        return database.requestQueries.findRequestResponse(
            path = params.path,
            host = params.host,
            params = params.params,
        )
            .executeAsOneOrNull()
            ?.let(::createResponseInfo)
    }

    override suspend fun setRequestEnabledState(id: Long, isEnabled: Boolean) {
        withContext(Dispatchers.IO) {
            database.requestQueries.setEnableState(
                id = id,
                isEnabled = if (isEnabled) REQUEST_ENABLED else REQUEST_DISABLED
            )
        }
    }

    override suspend fun updateRequestParams(id: Long, requestParams: RequestParams) {
        withContext(Dispatchers.IO) {
            database.requestQueries.updateRequestParams(
                id = id,
                host = requestParams.host,
                params = requestParams.params,
                path = requestParams.path
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