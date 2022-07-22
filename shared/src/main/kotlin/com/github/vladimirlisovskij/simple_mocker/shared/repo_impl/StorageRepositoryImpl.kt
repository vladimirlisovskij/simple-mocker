package com.github.vladimirlisovskij.simple_mocker.shared.repo_impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.github.vladimirlisovskij.simple_mocker.shared.RequestBodyProvider
import com.github.vladimirlisovskij.simple_mocker.shared.repo.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream

class StorageRepositoryImpl(
    private val context: Context
) : StorageRepository {
    override fun grantReadPermissionForUri(clientPackage: String, uri: Uri) {
        context.grantUriPermission(clientPackage, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    override fun getUriForFileName(fileName: String): Uri {
        val file = RequestBodyProvider.getFile(context, fileName)
        return RequestBodyProvider.getUriForFile(context, file)
    }

    override fun getFileNameByUri(uri: Uri): String? {
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

    override suspend fun tryReplaceFile(oldFileName: String, newFileUri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            val oldFile = RequestBodyProvider.getFile(context, oldFileName)
            val newFileInputStream = context.contentResolver.openInputStream(newFileUri)
                ?: return@withContext false

            runCatching {
                newFileInputStream.use { input ->
                    FileOutputStream(oldFile).use { output ->
                        input.copyTo(output)
                    }
                }
            }.isSuccess

        }
    }

    override suspend fun removeFile(fileName: String): Boolean {
        return withContext(Dispatchers.IO) {
            RequestBodyProvider.getFile(context, fileName).delete()
        }
    }

    override suspend fun tryCopyFile(fileUri: Uri): String? {
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