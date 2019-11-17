package com.kadirdogan97.sinifyoklama

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface YoklamaService {
    @GET("index.php")
    fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<Login>
}