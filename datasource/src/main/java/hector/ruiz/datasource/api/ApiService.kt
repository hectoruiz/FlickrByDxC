package hector.ruiz.datasource.api

import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.domain.photo.search.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    suspend fun searchPhoto(
        @Query(value = METHOD) method: String,
        @Query(value = TEXT) keywords: String
    ): Response<PhotosResponse>

    @GET
    suspend fun getPhotoInfo(
        @Query(value = METHOD) method: String,
        @Query(value = PHOTO_ID) idPhoto: String,
        @Query(value = SECRET) secret: String
    ): Response<PhotoResponse>

    private companion object {
        private const val METHOD = "method"
        private const val TEXT = "text"
        private const val PHOTO_ID = "photo_id"
        private const val SECRET = "secret"
    }
}
