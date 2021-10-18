package hector.ruiz.usecase.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.sizes.PhotoSizeResponse

interface GetSizesPhotoRepository {

    suspend fun getSizesPhoto(idPhoto: String): ResponseResult<PhotoSizeResponse>
}
