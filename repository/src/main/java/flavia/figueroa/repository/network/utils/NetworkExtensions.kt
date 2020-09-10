package flavia.figueroa.repository.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import java.text.SimpleDateFormat
import java.util.*

fun isAirplaneMode(context: Context): Boolean {
    val air = Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON, 0
    )
    return air != 0
}

fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.getNetworkCapabilities(cm.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
}

fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this) ?: Calendar.getInstance().time
}