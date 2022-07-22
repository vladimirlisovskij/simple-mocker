package com.github.vladimirlisovskij.simple_mocker.shared.di.module

import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository
import com.github.vladimirlisovskij.simple_mocker.shared.usecase.*
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

    @Provides
    fun provideRemoveRequestUseCase(
        dataBaseRepository: DataBaseRepository,
        storageRepository: StorageRepository
    ) = RemoveRequestUseCase(
        dataBaseRepository,
        storageRepository
    )

    @Provides
    fun provideUpdateRequestUseCase(
        dataBaseRepository: DataBaseRepository,
        storageRepository: StorageRepository
    ) = UpdateRequestUseCase(
        dataBaseRepository,
        storageRepository
    )
}
