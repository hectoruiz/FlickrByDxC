package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Visibility(
    val ispublic: Int?,
    val isfriend: Int?,
    val isfamily: Int?
)
