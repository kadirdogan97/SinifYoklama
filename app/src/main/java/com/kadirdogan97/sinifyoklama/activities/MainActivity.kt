package com.kadirdogan97.sinifyoklama.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirdogan97.sinifyoklama.util.toast
import android.net.wifi.WifiManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.LessonListener
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ActivityMainBinding
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.LessonService
import com.kadirdogan97.sinifyoklama.network.model.Student
import com.kadirdogan97.sinifyoklama.util.hide
import com.kadirdogan97.sinifyoklama.util.show
import com.kadirdogan97.sinifyoklama.viewmodels.AuthViewModel
import com.kadirdogan97.sinifyoklama.viewmodels.LessonsViewModel
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity(), LessonListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val viewModel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.lessonListener = this
        var myLogin = intent.getSerializableExtra("LoginUser") as Student
        viewModel.fetchLessonsData(myLogin.bolum_id);
        toast(getMacAdress())
    }

    override fun onStarted() {

        toast("Dersler Yükleniyor")
    }

    override fun onSuccess(loginResponse: LiveData<LessonService>) {
        loginResponse.observe(this, Observer {
            toast(it.toString())
        })
    }

    override fun onFailure(message: String?) {
        toast("Bir Sorun Oluştu")
    }

    fun getMacAdress(): String{
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        return wInfo.macAddress
    }
}
