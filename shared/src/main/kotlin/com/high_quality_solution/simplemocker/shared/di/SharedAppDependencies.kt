package com.high_quality_solution.simplemocker.shared.di

import android.content.Context
import com.high_quality_solution.simplemocker.shared.repo.DataBaseRepository
import com.high_quality_solution.simplemocker.shared.repo.StorageRepository

interface SharedAppDependencies {
    fun context(): Context
    fun dataBaseRepository(): DataBaseRepository
    fun storageRepository(): StorageRepository
}
