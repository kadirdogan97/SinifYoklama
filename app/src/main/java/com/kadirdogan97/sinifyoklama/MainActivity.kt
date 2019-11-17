package com.kadirdogan97.sinifyoklama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirdogan97.yoklasinifi.util.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myLogin = intent.getSerializableExtra("LoginUser") as Login
        toast(myLogin.success.toString())
    }
}
