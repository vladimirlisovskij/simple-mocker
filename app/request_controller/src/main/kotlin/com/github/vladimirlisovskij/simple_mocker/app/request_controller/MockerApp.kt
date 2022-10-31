package com.github.vladimirlisovskij.simple_mocker.app.request_controller

import android.app.Application
import com.github.vladimirlisovskij.simple_mocker.service.di.ServiceDiDependencies
import com.github.vladimirlisovskij.simple_mocker.service.di.ServiceDiResolver
import com.github.vladimirlisovskij.simple_mocker.shared.di.SharedDiDependencies
import com.github.vladimirlisovskij.simple_mocker.shared.di.SharedDiResolver
import com.github.vladimirlisovskij.simple_mocker.ui.di.UiDiDependencies
import com.github.vladimirlisovskij.simple_mocker.ui.di.UiDiResolver

class MockerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // initDependencies()
    }

    // private fun initDependencies() {
    //     SharedDiResolver.dependenciesCreator = {
    //         object : SharedDiDependencies {
    //             override fun context() = this@MockerApp
    //         }
    //     }
    //
    //     ServiceDiResolver.dependenciesCreator = {
    //         object : ServiceDiDependencies {
    //             override fun getMockResponseUseCase() = SharedDiResolver.getApi().getMockResponseUseCase()
    //         }
    //     }
    //
    //     UiDiResolver.dependenciesCreator = {
    //         object : UiDiDependencies {
    //             override fun context() = this@MockerApp
    //
    //             override fun getRequestListUseCase() = SharedDiResolver.getApi().getRequestListUseCase()
    //
    //             override fun createMockRequestUseCase() = SharedDiResolver.getApi().createMockRequestUseCase()
    //
    //             override fun getRequestByIdUseCase() = SharedDiResolver.getApi().getRequestByIdUseCase()
    //
    //             override fun getUriForFileNameUseCase() = SharedDiResolver.getApi().getUriForFileNameUseCase()
    //
    //             override fun getFileNameByUriUseCase() = SharedDiResolver.getApi().getFileNameByUriUseCase()
    //
    //             override fun setRequestEnabledStateUseCase() = SharedDiResolver.getApi().setRequestEnabledStateUseCase()
    //
    //             override fun removeRequestUseCase() = SharedDiResolver.getApi().removeRequestUseCase()
    //
    //             override fun updateRequestUseCase() = SharedDiResolver.getApi().updateRequestUseCase()
    //         }
    //     }
    // }
}
