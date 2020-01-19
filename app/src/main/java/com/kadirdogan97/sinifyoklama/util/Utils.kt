package com.kadirdogan97.sinifyoklama.util

import android.content.Context
import android.net.wifi.WifiManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(message: String?){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}
fun getDate(): String{

    var sdf:SimpleDateFormat = SimpleDateFormat("dd_MM_yyyy", Locale.getDefault())
    var currentDate:String = sdf.format(Date())
    return currentDate

}
fun getTimeWithSec(): String{

    var sdf:SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    var currentDateandTime:String = sdf.format(Date())
    return currentDateandTime

}
fun byteArrayToHexString( array: Array<Byte> ): String {

    var result = StringBuilder(array.size * 2)

    for ( byte in array ) {

        val toAppend =
            String.format("%2X", byte).replace(" ", "0") // hexadecimal
        result.append(toAppend).append("-")
    }
    result.setLength(result.length - 1) // remove last '-'

    return result.toString()
}

fun toMD5Hash( text: String ): String {

    var result = ""

    try {

        val md5 = MessageDigest.getInstance("MD5")
        val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()

        result = byteArrayToHexString(md5HashBytes)
    }
    catch ( e: Exception ) {

        result = "error: ${e.message}"
    }

    return result
}