package com.kadirdogan97.sinifyoklama.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.AuthListener
import com.kadirdogan97.sinifyoklama.network.LoginRepository

class AuthViewModel: ViewModel() {
    var username: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(username.isNullOrEmpty()||password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Student No or Password")
            return
        }
        val loginResponse = LoginRepository()
            .userLogin(username!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}