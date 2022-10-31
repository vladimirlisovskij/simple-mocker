package com.github.vladimirlisovskij.simple_mocker.feature.request_list_new.api

import androidx.compose.runtime.Composable

interface RequestListApi {
    fun screenStarter(): @Composable () -> Unit
}
