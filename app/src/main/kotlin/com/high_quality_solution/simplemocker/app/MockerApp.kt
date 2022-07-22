package com.high_quality_solution.simplemocker.app

import android.app.Application
import android.content.Context
import com.high_quality_solution.simplemocker.service.di.ServiceDiDependencies
import com.high_quality_solution.simplemocker.service.di.ServiceDiResolver
import com.high_quality_solution.simplemocker.shared.di.SharedDiDependencies
import com.high_quality_solution.simplemocker.shared.di.SharedDiResolver
import com.high_quality_solution.simplemocker.shared.usecase.*
import com.high_quality_solution.simplemocker.ui.di.UiDiDependencies
import com.high_quality_solution.simplemocker.ui.di.UiDiResolver

class MockerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        SharedDiResolver.dependenciesCreator = {
            object : SharedDiDependencies {
                override fun context() = this@MockerApp
            }
        }

        ServiceDiResolver.dependenciesCreator = {
            object : ServiceDiDependencies {
                override fun getMockResponseUseCase() = SharedDiResolver.getApi().getMockResponseUseCase()
            }
        }

        UiDiResolver.dependenciesCreator = {
            object : UiDiDependencies {
                override fun context() = this@MockerApp

                override fun getRequestListUseCase() = SharedDiResolver.getApi().getRequestListUseCase()

                override fun createMockRequestUseCase() = SharedDiResolver.getApi().createMockRequestUseCase()

                override fun getRequestByIdUseCase() = SharedDiResolver.getApi().getRequestByIdUseCase()

                override fun getUriForFileNameUseCase() = SharedDiResolver.getApi().getUriForFileNameUseCase()

                override fun getFileNameByUriUseCase() = SharedDiResolver.getApi().getFileNameByUriUseCase()

                override fun setRequestEnabledStateUseCase() = SharedDiResolver.getApi().setRequestEnabledStateUseCase()

                override fun removeRequestUseCase() = SharedDiResolver.getApi().removeRequestUseCase()
            }
        }
    }
}
