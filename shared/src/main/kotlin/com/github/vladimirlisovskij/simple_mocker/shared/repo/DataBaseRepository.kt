package com.github.vladimirlisovskij.simple_mocker.shared.repo

import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestParams
import com.github.vladimirlisovskij.simple_mocker.shared.dto.ResponseInfo
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    fun getMockRequests(): Flow<List<RequestInfo>>
    fun findRequestResponse(params: RequestParams): ResponseInfo?

    suspend fun insertMockRequest(requestInfo: RequestInfo)
    suspend fun getRequestById(id: Long): RequestInfo
    suspend fun removeMockRequestById(id: Long)
    suspend fun setRequestEnabledState(id: Long, isEnabled: Boolean)
    suspend fun updateRequestParams(id: Long, requestParams: RequestParams)
}
