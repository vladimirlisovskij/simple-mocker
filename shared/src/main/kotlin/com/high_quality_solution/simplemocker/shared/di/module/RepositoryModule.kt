package com.high_quality_solution.simplemocker.shared.di.module

import android.content.Context
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataBaseRepository(database: MockRequestsDatabase) = DataBaseRepository(database)

    @Provides
    @Singleton
    fun provideStorageRepository(context: Context) = StorageRepository(context)
}
