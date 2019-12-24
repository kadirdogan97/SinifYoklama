package com.kadirdogan97.sinifyoklama.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirdogan97.sinifyoklama.util.toast
import android.net.wifi.WifiManager
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.data.Login


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myLogin = intent.getSerializableExtra("LoginUser") as Login
        toast(getMacAdress())

        

    }

    fun getMacAdress(): String{
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        return wInfo.macAddress
    }
}
