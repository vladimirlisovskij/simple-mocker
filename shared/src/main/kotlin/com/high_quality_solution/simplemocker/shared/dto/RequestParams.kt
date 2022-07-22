package com.high_quality_solution.simplemocker.shared.dto

class RequestParams(
    val path: String,
    val params: String? = null,
    val host: String? = null
) {
    override fun toString(): String {
        return "${host ?: "*"}$path${paramsToString()}"
    }

    private fun paramsToString() = params
        ?.let { "?$it" }
        .orEmpty()
}