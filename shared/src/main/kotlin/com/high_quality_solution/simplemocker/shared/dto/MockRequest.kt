package com.high_quality_solution.simplemocker.shared.dto

class MockRequest(
    val appPackage: String,
    val requestParams: RequestParams
)

class RequestParams(
    val path: String,
    val params: String? = null,
    val host: String? = null
)
