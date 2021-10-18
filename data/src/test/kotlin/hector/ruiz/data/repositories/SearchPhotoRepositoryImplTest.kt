package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.photo.search.PhotosResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchPhotoRepositoryImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private val searchPhotoRepositoryImpl by lazy {
        SearchPhotoRepositoryImpl(networkDataSource)
    }

    @Test
    fun `searchPhotoRepositoryImpl requesting searchPhoto`() {
        val responseData = mockk<ResponseResult<PhotosResponse>>()

        coEvery { networkDataSource.searchPhoto(KEYWORD) } returns responseData
        val result = runBlocking {
            searchPhotoRepositoryImpl.searchPhoto(KEYWORD)
        }

        assertEquals(responseData, result)
    }

    private companion object {
        private const val KEYWORD = "keyword"
    }
}
