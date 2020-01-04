package com.kadirdogan97.sinifyoklama.network

import androidx.lifecycle.LiveData
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.LessonService
import com.kadirdogan97.sinifyoklama.network.model.Login

interface LessonListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<LessonService>)
    fun onFailure(message: String?)
}