package hector.ruiz.usecase.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.search.PhotosResponse

interface SearchPhotoRepository {

    suspend fun searchPhoto(keyword: String): ResponseResult<PhotosResponse>
}
