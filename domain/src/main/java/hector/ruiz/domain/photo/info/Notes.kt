package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Notes(
    val note: List<String?>?
)
