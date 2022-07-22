package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class GetRequestListUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    operator fun invoke(): Flow<List<RequestInfo>> {
        return dataBaseRepository.getMockRequests()
    }
}