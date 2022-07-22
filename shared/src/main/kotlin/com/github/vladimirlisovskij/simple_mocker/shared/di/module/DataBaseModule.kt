package com.github.vladimirlisovskij.simple_mocker.shared.di.module

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.shared.mock_requests_database.MockRequestsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    private const val REQUESTS_DATABASE_NAME = "MockRequestsDatabase"

    @Provides
    @Singleton
    fun provideDataBaseDriver(context: Context): SqlDriver {
        return AndroidSqliteDriver(MockRequestsDatabase.Schema, context, REQUESTS_DATABASE_NAME)
    }

    @Provides
    @Singleton
    fun provideDataBase(driver: SqlDriver): MockRequestsDatabase {
        return MockRequestsDatabase(driver)
    }
}
