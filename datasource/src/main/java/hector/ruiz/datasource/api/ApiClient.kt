package hector.ruiz.datasource.api

import hector.ruiz.datasource.BuildConfig
import hector.ruiz.datasource.interceptors.FlickrInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor(flickrInterceptor: FlickrInterceptor) {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(flickrInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private companion object {
        const val BASE_URL = "https://www.flickr.com/services/rest/"
        const val TIMEOUT = 20L
    }
}
