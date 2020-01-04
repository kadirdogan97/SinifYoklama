package com.kadirdogan97.sinifyoklama.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.network.LessonListener
import com.kadirdogan97.sinifyoklama.network.ModifyListener
import com.kadirdogan97.sinifyoklama.network.LessonsRepository

class LessonsViewModel: ViewModel() {
    var mainClickListener: MainClickListener?=null
    var lessonListener: LessonListener? = null
    var modifyListener: ModifyListener?= null
    fun fetchLessonsData(bolum_id: String?){
        lessonListener?.onStarted()
        if(bolum_id.isNullOrEmpty()){
            lessonListener?.onFailure("Bolum ID Hatalı")
            return
        }
        val lessonResponse = LessonsRepository()
            .getLessons(bolum_id!!)
        lessonListener?.onSuccess(lessonResponse)
    }
    fun fetchLessonsTData(ogr_gorevli_id: String?){
        lessonListener?.onStarted()
        if(ogr_gorevli_id.isNullOrEmpty()){
            lessonListener?.onFailure("Ogr_Gorevli ID Hatalı")
            return
        }
        val lessonResponse = LessonsRepository()
            .getLessonsT(ogr_gorevli_id!!)
        lessonListener?.onSuccess(lessonResponse)
    }
    fun setDeviceData(ogr_id: String?, ag_adres: String?){
        modifyListener?.onStartedModify()
        if(ogr_id.isNullOrEmpty()||ag_adres.isNullOrEmpty()){
            modifyListener?.onFailureModify("Ogr_Gorevli ID Hatalı")
            return
        }
        val deviceResponse = LessonsRepository()
            .setDevice(ogr_id,ag_adres)
        modifyListener?.onSuccessSetDevice(deviceResponse)
    }

    fun onSetDeviceClick(view: View){
        mainClickListener?.onSetDeviceClicked()
    }

    interface MainClickListener {
        fun onSetDeviceClicked()
    }
}