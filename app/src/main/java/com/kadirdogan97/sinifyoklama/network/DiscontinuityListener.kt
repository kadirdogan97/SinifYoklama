package com.kadirdogan97.sinifyoklama.network

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService

interface DiscontinuityListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<DiscontinuityService>)
    fun onFailure(message: String?)
}