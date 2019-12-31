package com.kadirdogan97.sinifyoklama.network.model

import java.io.Serializable

data class Discontinuity (
    val id : Int?,
    val ogr_ad_soyad : String?,
    val ders_adi : String?,
    val tarih : String?,
    val devamsizlik : String?
): Serializable