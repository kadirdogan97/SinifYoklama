package com.kadirdogan97.sinifyoklama.util

import android.content.Context
import android.net.wifi.WifiManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message: String?){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}