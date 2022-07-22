package com.github.vladimirlisovskij.simple_mocker.service.di

import com.github.vladimirlisovskij.simple_mocker.shared.usecase.GetMockResponseUseCase

interface ServiceDiDependencies {
    fun getMockResponseUseCase(): GetMockResponseUseCase
}
