package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

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
