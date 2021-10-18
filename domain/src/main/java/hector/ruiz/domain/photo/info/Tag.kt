package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    val id: String?,
    val author: String?,
    val authorname: String?,
    val raw: String?,
    val _content: String?,
    val machine_tag: Int?
)
