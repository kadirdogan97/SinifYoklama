package com.kadirdogan97.sinifyoklama.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.kadirdogan97.sinifyoklama.AuthListener
import com.kadirdogan97.sinifyoklama.LessonListener
import com.kadirdogan97.sinifyoklama.network.LessonsRepository
import com.kadirdogan97.sinifyoklama.network.LoginRepository

class LessonsViewModel: ViewModel() {
    var lessonListener: LessonListener? = null
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
}