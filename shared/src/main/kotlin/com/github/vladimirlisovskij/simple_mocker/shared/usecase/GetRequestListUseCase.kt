package com.github.vladimirlisovskij.simple_mocker.shared.usecase

import com.github.vladimirlisovskij.simple_mocker.shared.dto.RequestInfo
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class GetRequestListUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    operator fun invoke(): Flow<List<RequestInfo>> {
        return dataBaseRepository.getMockRequests()
    }
}
