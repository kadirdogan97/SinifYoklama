package com.kadirdogan97.sinifyoklama.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.Student
import com.kadirdogan97.sinifyoklama.util.toast

class LessonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_detail)
        var myLesson = intent.getSerializableExtra("lesson") as Lesson
        toast(myLesson.toString())

    }
}
