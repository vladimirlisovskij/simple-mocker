package com.github.vladimirlisovskij.simple_mocker.shared.dto

class RequestInfo(
    val id: Long,
    val requestParams: RequestParams,
    val bodyFileName: String,
    val isEnabled: Boolean
)
