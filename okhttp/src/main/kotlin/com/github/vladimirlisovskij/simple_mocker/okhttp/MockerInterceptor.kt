package com.github.vladimirlisovskij.simple_mocker.okhttp

import android.content.Context
import com.github.vladimirlisovskij.simple_mocker.aidl.MockRequestAidl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.charset.StandardCharsets

class MockerInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(chain.request())
        val mockerBinder = MockerServiceHelper.mockerBinder
            ?: return response

        val mockResponseInfo = runCatching {
            mockerBinder.getMockRequestParams(
                MockRequestAidl(
                    appPackage = context.packageName,
                    path = request.url.encodedPath,
                    host = request.url.host,
                    params = request.url.query?.let(::sortParams)
                )
            )
        }.getOrNull() ?: return response

        val fileInputStream = context.contentResolver.openInputStream(mockResponseInfo.bodyUri)
            ?: return response

        return try {
            val buffer = ByteArray(fileInputStream.available())
            fileInputStream.read(buffer)
            fileInputStream.close()
            val rawJson = String(buffer, StandardCharsets.UTF_8)
            response
                .newBuilder()
                .body(rawJson.toResponseBody(MIME_JSON.toMediaType()))
                .build()
        } catch (e: Exception) {
            response
        }
    }

    private fun sortParams(string: String): String {
        return string
            .split(ARG_SEPARATOR)
            .sorted()
            .joinToString(ARG_SEPARATOR)
    }

    companion object {
        private const val ARG_SEPARATOR = "&"
        private const val MIME_JSON = "application/json"
    }
}
