package com.kadirdogan97.sinifyoklama

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kadirdogan97.sinifyoklama.databinding.ItemLessonBinding
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import kotlinx.android.synthetic.main.item_lesson.view.*

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsItemViewHolder>() {

    private val lessonList = arrayListOf<Lesson>()

    fun setLessonList(lessonList: List<Lesson>) {
        this.lessonList.clear();
        this.lessonList.addAll(lessonList)
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsItemViewHolder = LessonsItemViewHolder.create(parent)

    override fun getItemCount(): Int = lessonList.size

    override fun onBindViewHolder(holder: LessonsItemViewHolder, position: Int) = holder.bind(lessonList[position])


    class LessonsItemViewHolder(private val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(lesson: Lesson){
            binding.nameLesson.text = lesson.ders_adi
            binding.ogrGorevliLesson.text = lesson.ogr_gorevli_ad_soyad
        }
        companion object{
            fun create(parent: ViewGroup): LessonsItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemLessonBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_lesson,
                    parent,
                    false
                )
                return LessonsItemViewHolder(binding)
            }
        }
    }

}