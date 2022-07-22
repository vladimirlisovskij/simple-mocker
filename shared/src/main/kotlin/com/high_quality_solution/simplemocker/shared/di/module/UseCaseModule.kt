package com.high_quality_solution.simplemocker.shared.di.module

import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository
import com.high_quality_solution.simplemocker.shared.usecase.GetMockResponseUseCase
import com.high_quality_solution.simplemocker.shared.usecase.GetRequestListUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {
    @Provides
    fun provideGetMockResponseUseCase(
        dataBaseRepository: DataBaseRepository,
        storageRepository: StorageRepository
    ) = GetMockResponseUseCase(dataBaseRepository, storageRepository)

    @Provides
    fun provideGetRequestListUseCase(
        dataBaseRepository: DataBaseRepository
    ) = GetRequestListUseCase(dataBaseRepository)
}
