package com.kadirdogan97.sinifyoklama.data

import java.io.Serializable

data class Student (
    val id : Int?,
    val ogr_no : String?,
    val ad_soyad : String?,
    val ag_adresi : String?,
    val giris_durumu : String?,
    val bolum_id : String?

): Serializable