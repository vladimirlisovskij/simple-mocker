package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.dto.NewRequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

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
