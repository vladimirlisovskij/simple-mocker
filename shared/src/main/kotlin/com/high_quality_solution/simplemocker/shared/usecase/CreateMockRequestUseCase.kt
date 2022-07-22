package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.dto.NewRequestInfo
import com.high_quality_solution.simplemocker.shared.dto.RequestInfo
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

class CreateMockRequestUseCase(
    private val storageRepository: StorageRepository,
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(newRequestInfo: NewRequestInfo) {
        val newFileName = storageRepository.tryCopyFile(newRequestInfo.fileUri) ?: return
        val requestInfo = RequestInfo(
            id = -1,
            requestParams = newRequestInfo.requestParams,
            bodyFileName = newFileName,
            isEnabled = true
        )

        dataBaseRepository.insertMockRequest(requestInfo)
    }
}
