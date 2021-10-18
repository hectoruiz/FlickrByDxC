package hector.ruiz.flickrbydxc.extensions

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.manageEmptyField(fieldToCheck: String?, resId: Int) =
    fieldToCheck?.takeIf {
        it.isNotBlank()
    } ?: this.context.getString(resId)

fun View.manageEmptyField(fieldToCheck: Long?, resId: Int): String {
    return if (fieldToCheck != null && fieldToCheck != 0L) {
        convertLongToTime(fieldToCheck)
    } else this.context.getString(resId)
}

private fun convertLongToTime(time: Long): String {
    val date = Date(time * 1000)
    val format = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}
