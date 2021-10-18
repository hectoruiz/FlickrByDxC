package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Editability(
    val cancomment: Int?,
    val canaddmeta: Int?
)
