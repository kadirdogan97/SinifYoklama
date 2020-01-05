package com.kadirdogan97.sinifyoklama.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.network.AuthListener
import com.kadirdogan97.sinifyoklama.viewmodels.AuthViewModel
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.network.model.Login
import com.kadirdogan97.sinifyoklama.databinding.ActivityLoginBinding
import com.kadirdogan97.sinifyoklama.network.model.LoginT
import com.kadirdogan97.sinifyoklama.util.hide
import com.kadirdogan97.sinifyoklama.util.show
import com.kadirdogan97.sinifyoklama.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
//        viewModel.username = "151307051"
//        viewModel.password = "k1"
        viewModel.username = "hyigit"
        viewModel.password = "hy1"
        binding.switchCompat.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if(p1){
                    binding.usernameEdit.hint = "Kullanici Adi"
                    viewModel.isChecked=true
                }else{
                    binding.usernameEdit.hint = "Ogrenci No"
                    viewModel.isChecked=false
                }
            }

        })
    }

    override fun onStarted() {
        toast("Login Started")
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<Login>) {
        val intent = Intent(this, MainActivity::class.java)
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it.success.toString())
            intent.putExtra("LoginUser", it.user)
            if(it.success!!){
                startActivity(intent)
            }
        })
    }
    override fun onSuccessT(loginResponse: LiveData<LoginT>) {
        val intent = Intent(this, MainActivity::class.java)
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it.success.toString())
            intent.putExtra("LoginUserT", it.user)
            if(it.success!!){
                startActivity(intent)
            }
        })
    }

    override fun onFailure(message: String?) {
        progress_bar.hide()
        toast("Login Failure")
    }



}
