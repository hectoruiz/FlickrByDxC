package hector.ruiz.flickrbydxc.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hector.ruiz.datasource.api.ApiClient
import hector.ruiz.datasource.interceptors.FlickrInterceptor
import hector.ruiz.flickrbydxc.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun flickrInterceptorProvider(@ApplicationContext context: Context): FlickrInterceptor {
        return FlickrInterceptor(
            context.getString(R.string.flickr_api_key)
        )
    }

    @Provides
    @Singleton
    fun apiClientProvider(flickrInterceptor: FlickrInterceptor): ApiClient {
        return ApiClient(flickrInterceptor)
    }

    @Provides
    @Singleton
    fun oKHttpClientProvider(apiClient: ApiClient): OkHttpClient {
        return apiClient.okHttpClient
    }

    @Provides
    @Singleton
    fun retrofitProvider(apiClient: ApiClient): Retrofit {
        return apiClient.retrofit
    }

    @Provides
    @Singleton
    fun iODispatcherProvider(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
