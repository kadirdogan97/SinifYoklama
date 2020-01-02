package com.kadirdogan97.sinifyoklama.viewmodels

import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.DiscontinuityListener
import com.kadirdogan97.sinifyoklama.network.LessonDetailRepository

class LessonDetailsViewModel: ViewModel(){
    var discontinuityListener: DiscontinuityListener? = null
    fun fetchDiscontinuityData(ders_id: String?, ogr_id: String?){
        discontinuityListener?.onStarted()
        if(ders_id.isNullOrEmpty()||ogr_id.isNullOrEmpty()){
            discontinuityListener?.onFailure("ders_id veya ogr_id hatali")
            return
        }
        val discontinuityResponse = LessonDetailRepository()
            .getDiscontinuity(ders_id,ogr_id)
        discontinuityListener?.onSuccess(discontinuityResponse)
    }
}