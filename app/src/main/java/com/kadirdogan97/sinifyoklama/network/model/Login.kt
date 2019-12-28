package com.kadirdogan97.sinifyoklama.network.model

import java.io.Serializable

data class Login (
    val success : Boolean?,
    val message : String?,
    val user : Student?
): Serializable