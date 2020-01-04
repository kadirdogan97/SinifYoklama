package com.kadirdogan97.sinifyoklama.network

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.network.model.ModifyResponse

interface ModifyListener{
    fun onStartedModify()
    fun onSuccessSetDisc(modifyResponse: LiveData<ModifyResponse>)
    fun onSuccessCloseDisc(modifyResponse: LiveData<ModifyResponse>)
    fun onSuccessCreateBarcode(modifyResponse: LiveData<ModifyResponse>)
    fun onSuccessSetDevice(modifyResponse: LiveData<ModifyResponse>)
    fun onFailureModify(message: String?)
}