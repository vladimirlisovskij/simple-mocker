package com.high_quality_solution.simplemocker.shared.usecase

import android.net.Uri
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

class GetFileNameByUriUseCase(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(uri: Uri): String? {
        return storageRepository.getFileNameByUri(uri)
    }
}