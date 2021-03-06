package hector.ruiz.flickrbydxc.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(stringId: Int, duration: Int) {
    val view = view
    view?.let {
        Snackbar.make(view, stringId, duration).show()
    }
}

fun Fragment.snackBarLong(stringId: Int) = snackBar(stringId, LENGTH_LONG)
