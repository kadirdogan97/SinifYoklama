package com.kadirdogan97.sinifyoklama.network

import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.LessonService
import com.kadirdogan97.sinifyoklama.network.model.Login
import retrofit2.Call
import retrofit2.http.*

interface YoklamaService {
    @GET("index.php")
    fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<Login>

    @GET("dersler.php")
    fun getLessons(
        @Query("bolum_id") bolum_id: String
    ): Call<LessonService>
}