package com.high_quality_solution.simplemocker.shared.di.module

import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository
import com.high_quality_solution.simplemocker.shared.usecase.*
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

    @Provides
    fun provideCreateMockRequestUseCase(
        storageRepository: StorageRepository,
        dataBaseRepository: DataBaseRepository
    ) = CreateMockRequestUseCase(
        storageRepository,
        dataBaseRepository
    )

    @Provides
    fun provideGetRequestByIdUseCase(
        dataBaseRepository: DataBaseRepository
    ) = GetRequestByIdUseCase(
        dataBaseRepository
    )

    @Provides
    fun provideGetUriForFileNameUseCase(
        storageRepository: StorageRepository
    ) = GetUriForFileNameUseCase(
        storageRepository
    )

    @Provides
    fun provideGetFileNameByUriUseCase(
        storageRepository: StorageRepository
    ) = GetFileNameByUriUseCase(
        storageRepository
    )

    @Provides
    fun provideSetRequestEnabledStateUseCase(
        dataBaseRepository: DataBaseRepository
    ) = SetRequestEnabledStateUseCase(
        dataBaseRepository
    )
}
