package com.high_quality_solution.simplemocker.shared.di.module

import android.content.Context
import com.high_quality_solution.simplemocker.shared.mock_requests_database.MockRequestsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides

@Module
object DataBaseModule {
    @Provides
    fun provideDataBaseDriver(context: Context) = AndroidSqliteDriver(MockRequestsDatabase.Schema, context) as SqlDriver

    @Provides
    fun provideDataBase(driver: SqlDriver): MockRequestsDatabase {
        return MockRequestsDatabase(driver)
    }
}
