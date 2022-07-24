package com.github.vladimirlisovskij.simple_mocker.core.datasource.database

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.core.datasource.database.requests.RequestsDataBase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBaseDriver(context: Context): SqlDriver {
        return AndroidSqliteDriver(RequestsDataBase.Schema, context, DATA_BASE_NAME)
    }

    @Provides
    @Singleton
    fun provideDataBase(driver: SqlDriver): RequestsDataBase {
        return RequestsDataBase(driver)
    }

    private companion object {
        const val DATA_BASE_NAME = "AppDataBase"
    }
}