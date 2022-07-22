package com.high_quality_solution.simplemocker.shared

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class RequestBodyProvider : FileProvider() {
    companion object {
        const val AUTHORITY = "com.high_quality_solution.simplemocker.shared.provider"
        const val REQUEST_BODY_PATH = "request_body"

        fun getFile(context: Context, fileName: String): File {
            val dirPath = File(context.filesDir, REQUEST_BODY_PATH)
            if (!dirPath.exists()) dirPath.mkdir()
            return File(dirPath, fileName)
        }

        fun getUriForFile(context: Context, file: File): Uri {
            return getUriForFile(context, AUTHORITY, file)
        }
    }
}
