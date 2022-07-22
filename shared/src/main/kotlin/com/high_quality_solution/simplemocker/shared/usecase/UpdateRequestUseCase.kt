package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.dto.EditedRequestInfo
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

class UpdateRequestUseCase(
    private val dataBaseRepository: DataBaseRepository,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(requestInfo: EditedRequestInfo) {
        if (requestInfo.newFileUri != null) {
            val oldRequestInfo = dataBaseRepository.getRequestById(requestInfo.id)
            val isReplaceSuccess = storageRepository.tryReplaceFile(
                oldRequestInfo.bodyFileName,
                requestInfo.newFileUri
            )

            if (!isReplaceSuccess) return
        }

        dataBaseRepository.updateRequestParams(requestInfo.id, requestInfo.requestParams)
    }
}