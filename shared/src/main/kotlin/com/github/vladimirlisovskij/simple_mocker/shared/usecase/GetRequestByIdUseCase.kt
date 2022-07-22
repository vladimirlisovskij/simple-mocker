package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository

class GetRequestByIdUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(id: Long): RequestInfo {
        return dataBaseRepository.getRequestById(id)
    }
}