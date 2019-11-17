package com.kadirdogan97.sinifyoklama

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoklamaServiceProvider {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.SERVER_URL)
        .build()


    val yoklamaService = retrofit.create<YoklamaService>(YoklamaService::class.java)
}