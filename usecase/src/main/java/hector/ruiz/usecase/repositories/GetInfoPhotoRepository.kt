package hector.ruiz.usecase.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoResponse

interface GetInfoPhotoRepository {

    suspend fun getInfoPhoto(idPhoto: String, secret: String): ResponseResult<PhotoResponse>
}