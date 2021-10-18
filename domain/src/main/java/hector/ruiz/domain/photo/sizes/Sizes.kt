package hector.ruiz.domain.photo.sizes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sizes(
    val canblog: Int?,
    val canprint: Int?,
    val candownload: Int?,
    val size: List<Size?>?
)
