package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geoperms(
    val ispublic: Int?,
    val iscontact: Int?,
    val isfriend: Int?,
    val isfamily: Int?
)
