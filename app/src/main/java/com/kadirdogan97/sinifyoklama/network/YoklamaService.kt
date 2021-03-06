package com.kadirdogan97.sinifyoklama.network

import com.kadirdogan97.sinifyoklama.network.model.*
import retrofit2.Call
import retrofit2.http.*

interface YoklamaService {
    @GET("index_ogr.php")
    fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<Login>

    @GET("dersler.php")
    fun getLessons(
        @Query("bolum_id") bolum_id: String
    ): Call<LessonService>

    @GET("devamsizliklar.php")
    fun getDiscontinuity(
        @Query("ders_id") ders_id: String,
        @Query("ogr_id") ogr_id: String
    ): Call<DiscontinuityService>


    @GET("index_ogr_gorevli.php")
    fun userLoginT(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<LoginT>

    @GET("dersler_ogr_gorevli.php")
    fun getLessonsT(
        @Query("ogr_gorevli_id") ogr_gorevli_id: String
    ): Call<LessonService>

    @GET("devamsizliklar_ogr_gorevli.php")
    fun getDiscontinuityT(
        @Query("ders_id") ders_id: String
    ): Call<DiscontinuityService>

    @GET("barkod_olustur.php")
    fun setBarcode(
        @Query("barkod") barkod: String,
        @Query("ders_id") ders_id: String
    ): Call<ModifyResponse>

    @GET("yoklama_kapat.php")
    fun closeDiscontinuity(
        @Query("ders_id") ders_id: String
    ): Call<ModifyResponse>

    @GET("yoklama_al.php")
    fun setDiscontinuity(
        @Query("ogr_id") ogr_id: String,
        @Query("ders_id") ders_id: String
    ): Call<ModifyResponse>

    @GET("cihaz_tanimla.php")
    fun setDevice(
        @Query("ogr_id") ogr_id: String,
        @Query("ag_adres") ag_adres: String
    ): Call<ModifyResponse>
}