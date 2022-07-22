package com.high_quality_solution.simplemocker.shared.repo

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.high_quality_solution.simplemocker.shared.RequestBodyProvider

class StorageRepository(
    private val context: Context
) {
    fun getReadUriForFileName(clientPackage: String, fileName: String): Uri {
        return RequestBodyProvider.getMockRequestFileUri(context, fileName)
            .also { context.grantUriPermission(clientPackage, it, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
    }
}
