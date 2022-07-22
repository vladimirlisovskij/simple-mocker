package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository

class SetRequestEnabledStateUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(id: Long, isEnabled: Boolean) {
        dataBaseRepository.setRequestEnabledState(id, isEnabled)
    }
}
