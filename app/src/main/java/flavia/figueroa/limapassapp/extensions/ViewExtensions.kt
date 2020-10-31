package flavia.figueroa.limapassapp.extensions

import android.app.Activity
import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import flavia.figueroa.repository.network.exception.GenericException
import java.util.*

fun Exception.getExceptionMessage(): String {
    return when(this) {
        is GenericException -> {
            this.msg
        }
        else -> {
            this.message ?: ""
        }
    }
}

fun Activity?.showDialog(resourceId: Int, func: Dialog.() -> Unit) {
    val dialog = Dialog(this ?: return)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
    dialog.setContentView(resourceId)
    dialog.func()
    dialog.show()
}

fun Activity.changeLenguageTo(lenguage: String = "es") {
    val locale = Locale(lenguage)
    val config =
        Configuration(resources.configuration)
    Locale.setDefault(locale)
    config.setLocale(locale)

    //TODO: Update deprecated function
    resources.updateConfiguration(
        config,
        this.resources.displayMetrics
    )

    recreate()
}