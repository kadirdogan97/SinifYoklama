package com.kadirdogan97.sinifyoklama.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.DiscontinuityListener
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.adapters.DiscontinuityAdapter
import com.kadirdogan97.sinifyoklama.databinding.ActivityLessonDetailBinding
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.Student
import com.kadirdogan97.sinifyoklama.util.toast
import com.kadirdogan97.sinifyoklama.viewmodels.LessonDetailsViewModel

class LessonDetailActivity : AppCompatActivity(), DiscontinuityListener{

    private val discontinuityAdapter = DiscontinuityAdapter()
    private lateinit var binding: ActivityLessonDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_lesson_detail
        )
        val viewModel = ViewModelProviders.of(this).get(LessonDetailsViewModel::class.java)
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = discontinuityAdapter
        viewModel.discontinuityListener = this
        var myLesson = intent.getSerializableExtra("lesson") as Lesson
        var myLogin = intent.getSerializableExtra("student") as Student
        viewModel.fetchDiscontinuityData(myLesson.id.toString(),myLogin.id.toString())
    }

    override fun onStarted() {}


    override fun onSuccess(discontinuityResponse: LiveData<DiscontinuityService>) {
        discontinuityResponse.observe(this, Observer {
            discontinuityAdapter.setDiscontinuityList(it.devamsizliklar!!)
        })
    }

    override fun onFailure(message: String?) {}
}
