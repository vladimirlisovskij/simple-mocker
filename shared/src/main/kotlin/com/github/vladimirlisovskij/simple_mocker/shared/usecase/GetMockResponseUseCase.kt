package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.dto.MockRequest
import com.github.vladimirlisovskij.simple_mocker.shared.dto.MockResponse
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

class GetMockResponseUseCase(
    private val dataBaseRepository: DataBaseRepository,
    private val storageRepository: StorageRepository
) {
    operator fun invoke(request: MockRequest): MockResponse? {
        return dataBaseRepository
            .findRequestResponse(request.requestParams)
            ?.let { info ->
                val bodyUri = storageRepository.getUriForFileName(info.bodyFileName)
                storageRepository.grantReadPermissionForUri(request.appPackage, bodyUri)
                MockResponse(bodyUri)
            }
    }
}
