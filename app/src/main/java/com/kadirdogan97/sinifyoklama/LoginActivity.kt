package com.kadirdogan97.sinifyoklama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.databinding.ActivityLoginBinding
import com.kadirdogan97.yoklasinifi.util.hide
import com.kadirdogan97.yoklasinifi.util.show
import com.kadirdogan97.yoklasinifi.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
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
            intent.putExtra("LoginUser", it)
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
