package com.github.vladimirlisovskij.simple_mocker.shared.di.module

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.shared.database.requests.RequestsDataBase
import com.github.vladimirlisovskij.simple_mocker.shared.repo.DataBaseRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository
import com.github.vladimirlisovskij.simple_mocker.shared.repo_impl.DataBaseRepositoryImpl
import com.github.vladimirlisovskij.simple_mocker.shared.repo_impl.StorageRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataBaseRepository(database: RequestsDataBase): DataBaseRepository {
        return DataBaseRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideStorageRepository(context: Context): StorageRepository {
        return StorageRepositoryImpl(context)
    }
}
