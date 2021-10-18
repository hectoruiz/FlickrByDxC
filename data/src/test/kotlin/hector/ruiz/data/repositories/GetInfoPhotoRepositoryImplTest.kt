package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.photo.info.PhotoResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetInfoPhotoRepositoryImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private val getInfoPhotoRepositoryImpl by lazy {
        GetInfoPhotoRepositoryImpl(networkDataSource)
    }

    @Test
    fun `getInfoPhotoRepositoryImpl requesting getInfoPhoto`() {
        val responseData = mockk<ResponseResult<PhotoResponse>>()

        coEvery { networkDataSource.getInfoPhoto(ID_PHOTO, SECRET) } returns responseData
        val result = runBlocking {
            getInfoPhotoRepositoryImpl.getInfoPhoto(ID_PHOTO, SECRET)
        }

        assertEquals(responseData, result)
    }

    private companion object {
        private const val ID_PHOTO = "id_photo"
        private const val SECRET = "secret"
    }
}