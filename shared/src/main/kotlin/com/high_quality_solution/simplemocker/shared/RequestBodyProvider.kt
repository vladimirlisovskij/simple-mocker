package com.high_quality_solution.simplemocker.shared

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class RequestBodyProvider : FileProvider() {
    companion object {
        private const val AUTHORITY = "com.high_quality_solution.simplemocker.shared.provider"
        private const val REQUEST_BODY_PATH = "request_body"

        fun getMockRequestFileUri(context: Context, fileName: String): Uri {
            val dirPath = File(context.filesDir, REQUEST_BODY_PATH)
            val bodyFile = File(dirPath, fileName)
            return getUriForFile(context, AUTHORITY, bodyFile)
        }
    }
}
