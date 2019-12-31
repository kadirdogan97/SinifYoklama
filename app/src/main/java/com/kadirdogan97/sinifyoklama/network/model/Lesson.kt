package com.kadirdogan97.sinifyoklama.network.model

import java.io.Serializable

data class Lesson (
    val id : Int?,
    val ogr_gorevli_ad_soyad : String?,
    val ogr_gorevli_mail : String?,
    val ders_adi : String?,
    val ders_gunu : String?,
    val baslangic_saati : String?,
    val bitis_saati : String?,
    val yoklama_aktif : String?,
    val barkod : String?
): Serializable