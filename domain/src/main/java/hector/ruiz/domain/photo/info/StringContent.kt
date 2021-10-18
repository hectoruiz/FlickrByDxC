package hector.ruiz.domain.photo.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StringContent(
    @Json(name = "_content")
    val data: String?
)
