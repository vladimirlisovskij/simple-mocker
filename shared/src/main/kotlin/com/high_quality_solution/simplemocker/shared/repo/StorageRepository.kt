package com.high_quality_solution.simplemocker.shared.repo

import android.net.Uri

interface StorageRepository {
    fun grantReadPermissionForUri(clientPackage: String, uri: Uri)
    fun getUriForFileName(fileName: String): Uri
    fun getFileNameByUri(uri: Uri): String?

    suspend fun tryReplaceFile(oldFileName: String, newFileUri: Uri): Boolean
    suspend fun removeFile(fileName: String): Boolean
    suspend fun tryCopyFile(fileUri: Uri): String?
}

