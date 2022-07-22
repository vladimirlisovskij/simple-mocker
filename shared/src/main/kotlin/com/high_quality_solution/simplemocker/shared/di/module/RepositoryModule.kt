package com.high_quality_solution.simplemocker.shared.di.module

import android.content.Context
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository
import com.high_quality_solution.simplemocker.shared.repo_impl.DataBaseRepositoryImpl
import com.high_quality_solution.simplemocker.shared.repo_impl.StorageRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataBaseRepository(database: MockRequestsDatabase): DataBaseRepository {
        return DataBaseRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideStorageRepository(context: Context): StorageRepository {
        return StorageRepositoryImpl(context)
    }
}
