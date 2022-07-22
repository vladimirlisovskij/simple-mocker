package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.dto.MockRequest
import com.high_quality_solution.simplemocker.shared.dto.MockResponse
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

class GetMockResponseUseCase(
    private val dataBaseRepository: DataBaseRepository,
    private val storageRepository: StorageRepository
) {
    operator fun invoke(request: MockRequest): MockResponse? {
        return dataBaseRepository
            .findRequest(request.requestParams)
            ?.let { info ->
                val bodyUri = storageRepository.getReadUriForFileName(
                    clientPackage = request.appPackage,
                    fileName = info.bodyFileName
                )

                MockResponse(bodyUri)
            }
    }
}
