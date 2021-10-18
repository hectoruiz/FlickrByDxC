package hector.ruiz.datasource.datasources

import hector.ruiz.datasource.api.ApiClient
import hector.ruiz.datasource.api.ApiService
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.domain.photo.search.PhotosResponse
import hector.ruiz.domain.photo.sizes.PhotoSizeResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class NetworkDataSourceImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var apiClient: ApiClient

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var apiService: ApiService

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val networkDataSourceImpl by lazy {
        NetworkDataSourceImpl(retrofit, coroutineDispatcher)
    }

    @Before
    fun setUp() {
        every { apiClient.retrofit } returns retrofit
        every { retrofit.create<ApiService>() } returns apiService
    }

    @Test
    fun `error requesting searchPhoto`() {
        coEvery { apiService.searchPhoto(SEARCH_METHOD, KEYWORD) } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.searchPhoto(KEYWORD)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting searchPhoto`() {
        val responseData = mockk<PhotosResponse>()
        coEvery { apiService.searchPhoto(SEARCH_METHOD, KEYWORD) } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        val result = runBlocking {
            networkDataSourceImpl.searchPhoto(KEYWORD)
        }

        assertNull(result.errorCode)
        assertEquals(responseData, result.data)
    }

    @Test
    fun `error requesting getCharacter`() {
        coEvery {
            apiService.getPhotoInfo(
                GET_INFO_METHOD,
                PHOTO_ID,
                SECRET
            )
        } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.getInfoPhoto(PHOTO_ID, SECRET)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting getCharacter`() {
        val responseData = mockk<PhotoResponse>()
        coEvery {
            apiService.getPhotoInfo(
                GET_INFO_METHOD,
                PHOTO_ID,
                SECRET
            )
        } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        val result = runBlocking {
            networkDataSourceImpl.getInfoPhoto(PHOTO_ID, SECRET)
        }

        assertNull(result.errorCode)
        assertEquals(responseData, result.data)
    }

    @Test
    fun `error requesting getSizesPhoto`() {
        coEvery {
            apiService.getPhotoSizes(
                GET_SIZES_METHOD,
                PHOTO_ID
            )
        } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.getSizesPhoto(PHOTO_ID)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting getSizesPhoto`() {
        val responseData = mockk<PhotoSizeResponse>()
        coEvery {
            apiService.getPhotoSizes(
                GET_SIZES_METHOD,
                PHOTO_ID
            )
        } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        val result = runBlocking {
            networkDataSourceImpl.getSizesPhoto(PHOTO_ID)
        }

        assertNull(result.errorCode)
        assertEquals(responseData, result.data)
    }

    private companion object {
        const val ERROR_CODE = 400
        const val SUCCESS_CODE = 200
        private const val SEARCH_METHOD = "flickr.photos.search"
        private const val GET_INFO_METHOD = "flickr.photos.getInfo"
        private const val GET_SIZES_METHOD = "flickr.photos.getSizes"
        private const val KEYWORD = "keyword"
        private const val PHOTO_ID = "photo_id"
        private const val SECRET = "secret"
    }
}
