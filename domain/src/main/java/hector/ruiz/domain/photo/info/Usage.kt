package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Usage(
    val candownload: Int?,
    val canblog: Int?,
    val canprint: Int?,
    val canshare: Int?
)
