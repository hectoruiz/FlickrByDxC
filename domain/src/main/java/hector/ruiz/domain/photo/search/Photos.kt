package hector.ruiz.domain.photo.search

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    val page: Int?,
    val pages: Int?,
    val perpage: Int?,
    val total: Int?,
    val photo: List<Photo?>?
)
