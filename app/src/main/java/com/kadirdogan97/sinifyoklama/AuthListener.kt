package com.kadirdogan97.sinifyoklama

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.network.model.Login

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<Login>)
    fun onFailure(message: String?)
}