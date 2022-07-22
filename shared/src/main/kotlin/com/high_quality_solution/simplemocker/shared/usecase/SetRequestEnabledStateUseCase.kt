package com.high_quality_solution.simplemocker.shared.usecase

import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository

class SetRequestEnabledStateUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(id: Long, isEnabled: Boolean) {
        dataBaseRepository.setRequestEnabledState(id, isEnabled)
    }
}
