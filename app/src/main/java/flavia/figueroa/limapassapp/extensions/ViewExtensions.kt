package flavia.figueroa.limapassapp.extensions

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import flavia.figueroa.repository.network.exception.GenericException

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