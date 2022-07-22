package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.dto.EditedRequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

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