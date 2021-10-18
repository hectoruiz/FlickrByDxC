package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val latitude: Double?,
    val longitude: Double?,
    val accuracy: Int?,
    val context: Int?,
    val locality: StringContent?,
    val county: StringContent?,
    val region: StringContent?,
    val country: StringContent?,
    val neighbourhood: StringContent?
)
