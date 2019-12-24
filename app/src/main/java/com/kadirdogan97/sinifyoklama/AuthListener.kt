package com.kadirdogan97.sinifyoklama

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.data.Login

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<Login>)
    fun onFailure(message: String?)
}