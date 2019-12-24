package com.kadirdogan97.sinifyoklama.data

import retrofit2.Call
import retrofit2.http.*

interface YoklamaService {
    @GET("index.php")
    fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<Login>
}