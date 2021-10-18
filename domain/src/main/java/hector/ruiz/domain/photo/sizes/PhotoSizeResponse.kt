package hector.ruiz.domain.photo.sizes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoSizeResponse(
    val sizes: Sizes?,
    val stat: String?
)
