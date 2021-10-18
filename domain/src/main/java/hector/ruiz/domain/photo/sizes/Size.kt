package hector.ruiz.domain.photo.sizes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Size(
    val label: String?,
    val width: Int?,
    val height: Int?,
    val source: String?,
    val url: String?,
    val media: String?
)
