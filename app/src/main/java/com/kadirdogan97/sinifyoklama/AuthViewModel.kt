package com.kadirdogan97.sinifyoklama

import android.view.View
import androidx.lifecycle.ViewModel

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
        val loginResponse = LoginRepository().userLogin(username!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}