package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates(
    val posted: Int?,
    val taken: String?,
    val takengranularity: Int?,
    val takenunknown: Int?,
    val lastupdate: Int?
)
