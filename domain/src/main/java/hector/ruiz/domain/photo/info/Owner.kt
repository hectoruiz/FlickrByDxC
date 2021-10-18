package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    val nsid: String?,
    val username: String?,
    val realname: String?,
    val location: String?,
    val iconserver: Int?,
    val iconfarm: Int?,
    val path_alias: String?
)
