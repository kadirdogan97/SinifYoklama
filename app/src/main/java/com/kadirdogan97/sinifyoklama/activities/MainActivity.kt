package com.kadirdogan97.sinifyoklama.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirdogan97.sinifyoklama.util.toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.network.LessonListener
import com.kadirdogan97.sinifyoklama.network.ModifyListener
import com.kadirdogan97.sinifyoklama.adapters.LessonsAdapter
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ActivityMainBinding
import com.kadirdogan97.sinifyoklama.network.model.*
import com.kadirdogan97.sinifyoklama.viewmodels.LessonsViewModel
import java.net.NetworkInterface
import java.util.*


class MainActivity : AppCompatActivity(), LessonListener,
    ModifyListener, LessonsAdapter.OnItemClickListener, LessonsViewModel.MainClickListener{



    private lateinit var binding: ActivityMainBinding

    private val lessonAdapter = LessonsAdapter()
    private var myLogin = Student(1,"","","","","")
    private var myLoginT = Teacher(1,"","")
    private var viewmodel = LessonsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        viewmodel = ViewModelProviders.of(this).get(LessonsViewModel::class.java)
        binding.viewmodel = viewmodel
        binding.recyclerView.adapter = lessonAdapter
        viewmodel.lessonListener = this
        viewmodel.modifyListener = this
        if(intent.hasExtra("LoginUser")) {
            myLogin = intent.getSerializableExtra("LoginUser") as Student
            viewmodel.fetchLessonsData(myLogin.bolum_id);
        }
        if(intent.hasExtra("LoginUserT")) {
            myLoginT = intent.getSerializableExtra("LoginUserT") as Teacher
            viewmodel.fetchLessonsTData(myLoginT.id.toString())
        }
        if(!myLogin.ag_adresi.equals(getMacAddr())&&intent.hasExtra("LoginUser")){
            toast("hatalı cihaz")
            toast(getMacAddr())
            onBackPressed()
        }


    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("LoginUser")) {
            viewmodel.fetchLessonsData(myLogin.bolum_id);
        }
        if(intent.hasExtra("LoginUserT")) {
            viewmodel.fetchLessonsTData(myLoginT.id.toString())
        }
    }

    override fun onStarted() {

        toast("Dersler Yükleniyor")
    }

    override fun onSuccess(loginResponse: LiveData<LessonService>) {
        loginResponse.observe(this, Observer {
            lessonAdapter.setOnItemClickListener(this)
            lessonAdapter.setLessonList(it.dersler!!)
            if(intent.hasExtra("LoginUserT")) {
                lessonAdapter.setTeacher(true)
            }
        })
    }
    override fun onItemClick(lesson: Lesson) {
        val intentD = Intent(this@MainActivity, LessonDetailActivity::class.java)
        intentD.putExtra("lesson",lesson)
        if(intent.hasExtra("LoginUser")) {
            intentD.putExtra("LoginUser",myLogin)
        }
        if(intent.hasExtra("LoginUserT")) {
            intentD.putExtra("LoginUserT",myLoginT)
        }
        startActivity(intentD)

    }

    override fun onFailure(message: String?) {
        toast("Bir Sorun Oluştu")
    }
    fun getMacAddr(): String {
        try {
            val all = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (!nif.getName().equals("wlan0", ignoreCase=true)) continue

                val macBytes = nif.getHardwareAddress() ?: return ""

                val res1 = StringBuilder()
                for (b in macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b))
                }

                if (res1.length > 0) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        } catch (ex: Exception) {
        }

        return "02:00:00:00:00:00"
    }

    override fun onStartedModify() {}

    override fun onSuccessSetDisc(modifyResponse: LiveData<ModifyResponse>) {}

    override fun onSuccessCloseDisc(modifyResponse: LiveData<ModifyResponse>) {}

    override fun onSuccessCreateBarcode(modifyResponse: LiveData<ModifyResponse>) {}

    override fun onSuccessSetDevice(modifyResponse: LiveData<ModifyResponse>) {
        modifyResponse.observe(this, Observer {
            toast(it.message)
        })
    }
    override fun onFailureModify(message: String?) {
        toast(message)
    }

    override fun onSetDeviceClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
