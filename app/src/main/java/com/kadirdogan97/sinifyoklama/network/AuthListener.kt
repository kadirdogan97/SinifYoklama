package com.kadirdogan97.sinifyoklama.network

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.network.model.Login
import com.kadirdogan97.sinifyoklama.network.model.LoginT

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<Login>)
    fun onSuccessT(loginResponse: LiveData<LoginT>)
    fun onFailure(message: String?)
}