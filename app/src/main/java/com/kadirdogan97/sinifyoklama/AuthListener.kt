package com.kadirdogan97.sinifyoklama

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<Login>)
    fun onFailure(message: String?)
}