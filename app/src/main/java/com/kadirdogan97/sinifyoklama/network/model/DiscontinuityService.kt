package com.kadirdogan97.sinifyoklama.network.model

import java.io.Serializable

data class DiscontinuityService (
    val success : Boolean?,
    val barkod : String?,
    val devamsizlik_sayi : String?,
    val devamsizliklar : List<Discontinuity>?
): Serializable