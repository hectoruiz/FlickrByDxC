package hector.ruiz.domain.photo.info

import com.squareup.moshi.JsonClass
import hector.ruiz.domain.photo.sizes.Size
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class PhotoInfo(
    val id: String?,
    val secret: String?,
    val server: Int?,
    val farm: Int?,
    val dateuploaded: String?,
    val isfavorite: String?,
    val license: String?,
    val safety_level: String?,
    val rotation: Int?,
    val originalsecret: String?,
    val originalformat: String?,
    val owner: Owner?,
    val title: StringContent?,
    val description: StringContent?,
    val visibility: Visibility?,
    val dates: Dates?,
    val views: String?,
    val editability: Editability?,
    val publiceditability: Editability?,
    val usage: Usage?,
    val comments: IntContent?,
    val notes: Notes?,
    val people: People?,
    val tags: Tags?,
    val location: Location?,
    val geoperms: Geoperms?,
    val urls: Urls?,
    val media: String?,
    var sizeList: List<Size?>?
) : Serializable
