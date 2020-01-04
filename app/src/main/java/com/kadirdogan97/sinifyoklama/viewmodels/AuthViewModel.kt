package com.kadirdogan97.sinifyoklama.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.network.AuthListener
import com.kadirdogan97.sinifyoklama.network.LoginRepository

class AuthViewModel: ViewModel() {
    var username: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var isChecked: Boolean = false
    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(username.isNullOrEmpty()||password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Username or Password")
            return
        }
        if(isChecked){
            val loginResponse = LoginRepository()
                .userLoginT(username!!, password!!)
            authListener?.onSuccessT(loginResponse)
        }else{
            val loginResponse = LoginRepository()
                .userLogin(username!!, password!!)
            authListener?.onSuccess(loginResponse)
        }

    }
}