package com.kadirdogan97.sinifyoklama.network.model
import java.io.Serializable

data class LessonService (
    val success : Boolean?,
    val dersler : List<Lesson>?
): Serializable