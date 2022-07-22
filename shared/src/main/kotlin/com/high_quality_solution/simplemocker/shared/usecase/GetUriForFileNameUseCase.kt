package com.high_quality_solution.simplemocker.shared.usecase

import android.net.Uri
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

class GetUriForFileNameUseCase(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(fileName: String): Uri {
        return storageRepository.getUriForFileName(fileName)
    }
}
