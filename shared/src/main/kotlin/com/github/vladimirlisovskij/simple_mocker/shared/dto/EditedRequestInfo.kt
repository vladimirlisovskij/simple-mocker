package com.github.vladimirlisovskij.simple_mocker.shared.dto

import android.net.Uri

class EditedRequestInfo(
    val id: Long,
    val requestParams: RequestParams,
    val newFileUri: Uri?
)