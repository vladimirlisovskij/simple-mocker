package com.high_quality_solution.simplemocker.shared_logic.usecase

import com.high_quality_solution.simplemocker.shared_logic.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared_logic.repo.StorageRepository
import com.high_quality_solution.simplemocker.shared_logic.dto.MockRequest
import com.high_quality_solution.simplemocker.shared_logic.dto.MockResponse

class GetMockResponseUseCase(
    private val dataBaseRepository: DataBaseRepository,
    private val storageRepository: StorageRepository
) {
    operator  fun invoke(request: MockRequest): MockResponse? {
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

