package hector.ruiz.domain.photo.search

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponse(
    val photos: Photos?,
    val stat: String?
)
