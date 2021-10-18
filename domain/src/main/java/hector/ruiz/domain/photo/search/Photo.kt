package hector.ruiz.domain.photo.search

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val id: String?,
    val owner: String?,
    val secret: String?,
    val server: Int?,
    val farm: Int?,
    val title: String?,
    val ispublic: Int?,
    val isfriend: Int?,
    val isfamily: Int?
)
