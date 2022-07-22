package com.github.vladimirlisovskij.simple_mocker.shared

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class RequestBodyProvider : FileProvider() {
    companion object {
        private const val AUTHORITY = "com.github.vladimirlisovskij.simple_mocker.shared.provider"

        fun getFile(context: Context, fileName: String): File {
            return File(context.filesDir, fileName)
        }

        fun getUriForFile(context: Context, file: File): Uri {
            return getUriForFile(context, AUTHORITY, file)
        }
    }
}
