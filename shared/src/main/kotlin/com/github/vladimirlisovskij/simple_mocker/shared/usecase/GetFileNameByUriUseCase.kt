package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import android.net.Uri
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

class GetFileNameByUriUseCase(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(uri: Uri): String? {
        return storageRepository.getFileNameByUri(uri)
    }
}