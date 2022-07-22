package com.high_quality_solution.simplemocker.shared.dto

import android.net.Uri

class EditedRequestInfo(
    val id: Long,
    val requestParams: RequestParams,
    val newFileUri: Uri?
)