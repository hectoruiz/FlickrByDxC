package hector.ruiz.usecase.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoInfo
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.domain.photo.search.Photo
import hector.ruiz.domain.photo.search.Photos
import hector.ruiz.domain.photo.search.PhotosResponse
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import hector.ruiz.usecase.repositories.SearchPhotoRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPhotoUseCaseTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var searchPhotoRepository: SearchPhotoRepository

    @MockK
    private lateinit var getInfoPhotoRepository: GetInfoPhotoRepository

    private val useCase by lazy {
        GetPhotoUseCase(searchPhotoRepository, getInfoPhotoRepository, Dispatchers.IO)
    }

    @Test
    fun `runUseCase request getListPhoto`() {
        val searchResponse = ResponseResult(
            null, PhotosResponse(
                Photos(
                    1, 2, 3, 10, listOf(
                        Photo(
                            ID_PHOTO, "owner", SECRET, null,
                            null, null, null, null, null
                        )
                    )
                ), "ok"
            )
        )
        coEvery { searchPhotoRepository.searchPhoto(KEYWORD) } returns searchResponse

        val photo = searchResponse.data?.photos?.photo?.get(0)
        val getInfoResponse = ResponseResult(
            null, PhotoResponse(
                PhotoInfo(
                    photo?.id.orEmpty(), photo?.secret.orEmpty(), 1, 0, "",
                    "", "", "", 0, "",
                    "", null, null, null, null,
                    null, "", null, null, null,
                    null, null, null, null, null,
                    null, null, ""
                ), "ok"
            )
        )
        coEvery {
            getInfoPhotoRepository.getInfoPhoto(photo?.id.orEmpty(), photo?.secret.orEmpty())
        } returns getInfoResponse

        val result = runBlocking { useCase(KEYWORD) }

        assertEquals(getInfoResponse.data, result[0].data)
    }

    private companion object {
        private const val KEYWORD = "keyword"
        private const val ID_PHOTO = "id_photo"
        private const val SECRET = "secret"
    }
}
