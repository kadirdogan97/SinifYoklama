package com.kadirdogan97.sinifyoklama.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kadirdogan97.sinifyoklama.network.model.*
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
                    tempLesson.add(Lesson(1, "1", "1", "1", "1", "1", "1", "1", "1",null))
                    val tempLessonService = LessonService(true,tempLesson)
                    lessonResponse.value = tempLessonService
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<LessonService>, response: Response<LessonService>) {
                    Log.d("1", "test: "+response.body())
                    lessonResponse.value = response.body()

                }

            })

        return lessonResponse
    }

    fun getLessonsT(ogr_gorevli_id: String) : LiveData<LessonService> {
        val lessonResponse = MutableLiveData<LessonService>()
        yoklamaServiceProvider.yoklamaService.getLessonsT(ogr_gorevli_id)
            .enqueue(object: Callback<LessonService>{
                override fun onFailure(call: Call<LessonService>, t: Throwable) {
                    val tempLesson = ArrayList<Lesson>()
                    tempLesson.add(Lesson(1, "1", "1", "1", "1", "1", "1", "1", "1",null))
                    val tempLessonService = LessonService(true,tempLesson)
                    lessonResponse.value = tempLessonService
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<LessonService>, response: Response<LessonService>) {
                    Log.d("1", "test: "+response.body())
                    lessonResponse.value = response.body()

                }

            })

        return lessonResponse
    }
    fun setDevice(ogr_id: String, ag_adres: String) : LiveData<ModifyResponse> {
        val deviceResponse = MutableLiveData<ModifyResponse>()
        yoklamaServiceProvider.yoklamaService.setDevice(ogr_id,ag_adres)
            .enqueue(object: Callback<ModifyResponse> {
                override fun onFailure(call: Call<ModifyResponse>, t: Throwable) {
                    val tempResponse = ModifyResponse(false,"yoklama alırken bir sıkıntı oluştu")
                    deviceResponse.value = tempResponse
                    Log.d("1", "test: "+t.message)
                }

                override fun onResponse(call: Call<ModifyResponse>, response: Response<ModifyResponse>) {
                    Log.d("1", "test: "+response.body())
                    deviceResponse.value = response.body()
                }

            })

        return deviceResponse
    }

}