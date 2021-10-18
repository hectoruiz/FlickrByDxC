package hector.ruiz.datasource.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.datasource.api.ApiService
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.domain.photo.search.PhotosResponse
import hector.ruiz.domain.photo.sizes.PhotoSizeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) : NetworkDataSource {

    private val service = retrofit.create<ApiService>()

    override suspend fun searchPhoto(keyword: String): ResponseResult<PhotosResponse> {
        return withContext(dispatcher) {
            service.searchPhoto(SEARCH_METHOD, keyword).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    override suspend fun getInfoPhoto(
        idPhoto: String,
        secret: String
    ): ResponseResult<PhotoResponse> {
        return withContext(dispatcher) {
            service.getPhotoInfo(GET_INFO_METHOD, idPhoto, secret).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    override suspend fun getSizesPhoto(idPhoto: String): ResponseResult<PhotoSizeResponse> {
        return withContext(dispatcher) {
            service.getPhotoSizes(GET_SIZES_METHOD, idPhoto).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    private companion object {
        private const val SEARCH_METHOD = "flickr.photos.search"
        private const val GET_INFO_METHOD = "flickr.photos.getInfo"
        private const val GET_SIZES_METHOD = "flickr.photos.getSizes"
    }
}
