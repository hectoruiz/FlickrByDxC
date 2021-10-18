package hector.ruiz.datasource.interceptors

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Test

class FlickrInterceptorTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var chain: Interceptor.Chain

    @MockK
    private lateinit var request: Request

    @MockK
    private lateinit var httpUrl: HttpUrl

    @MockK
    private lateinit var httpUrlBuilder: HttpUrl.Builder

    @MockK
    private lateinit var requestBuilder: Request.Builder

    @MockK
    private lateinit var response: Response

    private val flickrInterceptor by lazy {
        FlickrInterceptor(VALUE_API_KEY)
    }

    @Before
    fun setUp() {
        every { chain.request() } returns request
        every { request.url } returns httpUrl
        every { httpUrl.newBuilder() } returns httpUrlBuilder
        every { httpUrlBuilder.addQueryParameter(any(), any()) } returns httpUrlBuilder
        every { httpUrlBuilder.build() } returns httpUrl
        every { request.newBuilder() } returns requestBuilder
        every { requestBuilder.url(any<HttpUrl>()) } returns requestBuilder
        every { requestBuilder.build() } returns request
        every { chain.proceed(any()) } returns response
    }

    @Test
    fun `the interceptor add headers correctly`() {
        flickrInterceptor.intercept(chain)

        verifySequence {
            httpUrlBuilder.addQueryParameter(API_KEY, VALUE_API_KEY)
            httpUrlBuilder.addQueryParameter(FORMAT, JSON)
            httpUrlBuilder.build()
        }
    }

    private companion object {
        const val API_KEY = "apikey"
        const val VALUE_API_KEY = "api_key"
        const val FORMAT = "format"
        const val JSON = "json"
    }
}
