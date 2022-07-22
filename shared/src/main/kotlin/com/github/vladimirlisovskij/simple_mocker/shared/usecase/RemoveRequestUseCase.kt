package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository

class RemoveRequestUseCase(
    private val dataBaseRepository: DataBaseRepository,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(id: Long) {
        val fileName = dataBaseRepository.getRequestById(id).bodyFileName
        if (storageRepository.removeFile(fileName)) {
            dataBaseRepository.removeMockRequestById(id)
        }
    }
}
