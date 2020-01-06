package com.kadirdogan97.sinifyoklama.util

import android.content.Context
import android.net.wifi.WifiManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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

fun getTimeWithSec(): String{

    var sdf:SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    var currentDateandTime:String = sdf.format(Date())
    return currentDateandTime

}