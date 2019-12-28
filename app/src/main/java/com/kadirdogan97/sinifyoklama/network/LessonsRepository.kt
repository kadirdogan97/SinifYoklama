package com.kadirdogan97.sinifyoklama.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.LessonService
import com.kadirdogan97.sinifyoklama.network.model.Login
import com.kadirdogan97.sinifyoklama.network.model.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LessonsRepository {

    val yoklamaServiceProvider = YoklamaServiceProvider()

    fun getLessons(bolum_id: String) : LiveData<LessonService> {
        val lessonResponse = MutableLiveData<LessonService>()
        yoklamaServiceProvider.yoklamaService.getLessons(bolum_id)
            .enqueue(object: Callback<LessonService>{
                override fun onFailure(call: Call<LessonService>, t: Throwable) {
                    val tempLesson = ArrayList<Lesson>()
                    tempLesson.add(Lesson(1, "1", "1", "1", "1", "1", "1", "1"))
                    val tempLessonService = LessonService(true,tempLesson)
                    lessonResponse.value = tempLessonService
                    Log.d("1", "fail: "+t.message)
                }

                override fun onResponse(call: Call<LessonService>, response: Response<LessonService>) {
                    lessonResponse.value = response.body()

                }

            })

        return lessonResponse
    }
}