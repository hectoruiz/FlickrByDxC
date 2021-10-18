package hector.ruiz.flickrbydxc.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import hector.ruiz.domain.photo.sizes.Size
import hector.ruiz.flickrbydxc.R

fun Picasso.loadImage(
    sizes: List<Size?>?,
    requiredSize: String,
    appCompatImageView: AppCompatImageView,
    circularProgressIndicator: CircularProgressIndicator
) {
    this
        .load(
            sizes?.first {
                it?.label?.contains(requiredSize) == true
            }?.source ?: "",
        )
        .centerInside()
        .fit()
        .placeholder(R.drawable.ic_photo_placeholder)
        .error(R.drawable.ic_photo_error)
        .into(appCompatImageView, object : Callback {
            override fun onSuccess() {
                circularProgressIndicator.isVisible = false
            }

            override fun onError(e: Exception?) {
                circularProgressIndicator.isVisible = false
                e?.printStackTrace()
            }
        })
}
