package hector.ruiz.data.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.domain.photo.search.PhotosResponse
import hector.ruiz.domain.photo.sizes.PhotoSizeResponse

interface NetworkDataSource {

    suspend fun searchPhoto(keyword: String): ResponseResult<PhotosResponse>

    suspend fun getInfoPhoto(idPhoto: String, secret: String): ResponseResult<PhotoResponse>

    suspend fun getSizesPhoto(idPhoto: String): ResponseResult<PhotoSizeResponse>
}
