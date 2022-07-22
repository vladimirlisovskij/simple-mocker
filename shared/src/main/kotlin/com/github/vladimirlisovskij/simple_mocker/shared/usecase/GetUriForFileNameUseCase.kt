package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import android.net.Uri
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

class GetUriForFileNameUseCase(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(fileName: String): Uri {
        return storageRepository.getUriForFileName(fileName)
    }
}
