package com.kadirdogan97.sinifyoklama.network.model

import java.io.Serializable

data class LoginT (
    val success : Boolean?,
    val message : String?,
    val user : Teacher?
): Serializable