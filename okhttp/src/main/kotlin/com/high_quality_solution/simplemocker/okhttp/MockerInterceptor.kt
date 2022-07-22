package com.high_quality_solution.simplemocker.okhttp

import android.content.Context
import com.high_quality_solution.simplemocker.aidl.MockRequestAidl
import com.high_quality_solution.simplemocker.aidl.MockerBinder
import okhttp3.Interceptor
import okhttp3.Response

class MockerInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return MockerServiceHelper.mockerBinder
            ?.let { interceptWithService(chain, it) }
            ?: chain.proceed(chain.request())
    }

    private fun interceptWithService(chain: Interceptor.Chain, mockerBinder: MockerBinder): Response {
        val request = chain.request()
        val mockResponse = mockerBinder.getMockRequestParams(
            MockRequestAidl(
                appPackage = context.packageName,
                path = request.url.encodedPath
            )
        ) /*?: return chain.proceed(chain.request())*/

        return chain.proceed(chain.request())
    }
}
