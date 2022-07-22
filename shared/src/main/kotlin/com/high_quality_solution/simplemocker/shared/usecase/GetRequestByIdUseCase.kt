package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository

class GetRequestByIdUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(id: Long): RequestInfo {
        return dataBaseRepository.getRequestById(id)
    }
}