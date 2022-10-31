package com.github.vladimirlisovskij.simple_mocker.core.database

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.common.component_holder.HolderApi
import com.github.vladimirlisovskij.simple_mocker.common.component_holder.HolderDependencies
import com.github.vladimirlisovskij.simple_mocker.common.component_holder.WeakComponentHolder
import com.github.vladimirlisovskij.simple_mocker.core.datasource.database.requests.RequestsDataBase
import com.squareup.sqldelight.android.AndroidSqliteDriver

interface DataBaseDependencies : HolderDependencies {
    val context: Context
}

interface DataBaseApi : HolderApi {
    val dataBase: RequestsDataBase
}

object DataBaseComponentHolder : WeakComponentHolder<DataBaseDependencies, DataBaseApi>() {
    private const val DATA_BASE_NAME = "AppDataBase"

    override fun createApi(dependencies: DataBaseDependencies): DataBaseApi {
        val driver = AndroidSqliteDriver(RequestsDataBase.Schema, dependencies.context, DATA_BASE_NAME)
        val dataBase = RequestsDataBase(driver)
        return object : DataBaseApi {
            override val dataBase = dataBase
        }
    }
}