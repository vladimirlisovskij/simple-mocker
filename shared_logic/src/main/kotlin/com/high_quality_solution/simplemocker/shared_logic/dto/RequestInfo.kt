package com.high_quality_solution.simplemocker.shared_logic.dto

class RequestInfo(
    val id: Long,
    val path: String,
    val params: String?,
    val host: String?,
    val bodyFileName: String
)