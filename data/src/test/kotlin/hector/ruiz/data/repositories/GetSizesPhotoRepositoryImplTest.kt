package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.photo.sizes.PhotoSizeResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSizesPhotoRepositoryImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private val getSizesPhotoRepositoryImpl by lazy {
        GetSizesPhotoRepositoryImpl(networkDataSource)
    }

    @Test
    fun `getSizesPhotoRepositoryImpl requesting getSizesPhoto`() {
        val responseData = mockk<ResponseResult<PhotoSizeResponse>>()

        coEvery { networkDataSource.getSizesPhoto(ID_PHOTO) } returns responseData
        val result = runBlocking {
            getSizesPhotoRepositoryImpl.getSizesPhoto(ID_PHOTO)
        }

        assertEquals(responseData, result)
    }

    private companion object {
        private const val ID_PHOTO = "idPhoto"
    }
}
