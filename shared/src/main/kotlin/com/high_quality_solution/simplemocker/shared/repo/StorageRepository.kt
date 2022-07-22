package com.high_quality_solution.simplemocker.shared.repo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.high_quality_solution.simplemocker.shared.RequestBodyProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class StorageRepository(
    private val context: Context
) {
    fun grantReadPermissionForUri(clientPackage: String, uri: Uri) {
        context.grantUriPermission(clientPackage, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    fun getUriForFileName(fileName: String): Uri {
        val file = RequestBodyProvider.getFile(context, fileName)
        return RequestBodyProvider.getUriForFile(context, file)
    }

    fun getFileNameByUri(uri: Uri): String? {
        return when (uri.scheme) {
            SCHEME_FILE -> {
                uri.lastPathSegment
            }

            SCHEME_CONTENT -> {
                context.contentResolver
                    .query(uri, null, null, null, null)
                    ?.use { cursor ->
                        val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        cursor.moveToFirst()
                        cursor.getString(index)
                    }
            }

            else -> null
        }
    }

    fun replaceFile(oldFileName: String, newFileUri: Uri) {

    }

    fun removeFile(fileName: String): Boolean {
        return RequestBodyProvider.getFile(context, fileName).delete()
    }

    suspend fun tryCopyFile(fileUri: Uri): String? {
        return runCatching { copyFile(fileUri) }.getOrNull()
    }

    private suspend fun copyFile(fileUri: Uri): String? {
        return withContext(Dispatchers.IO) {
            val userFileInputStream = context.contentResolver.openInputStream(fileUri)
                ?: return@withContext null

            val newFileName = "${System.currentTimeMillis()}.json"
            val newFile = RequestBodyProvider.getFile(context, newFileName)
            newFile.createNewFile()
            val newFileOutputStream = FileOutputStream(newFile)
            userFileInputStream.use { input ->
                newFileOutputStream.use { output ->
                    input.copyTo(output)
                }
            }

            return@withContext newFileName
        }
    }

    companion object {
        private const val SCHEME_FILE = "file"
        private const val SCHEME_CONTENT = "content"
    }
}
