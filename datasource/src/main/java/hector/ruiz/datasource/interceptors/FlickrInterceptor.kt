package hector.ruiz.datasource.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class FlickrInterceptor @Inject constructor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val defaultUrl = request.url

        val url = defaultUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        val requestBuilder = request.newBuilder().url(url)
        return chain.proceed(requestBuilder.build())
    }

    private companion object {
        const val API_KEY = "apikey"
    }
}
