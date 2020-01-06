package com.kadirdogan97.sinifyoklama.adapters

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ItemLessonBinding
import com.kadirdogan97.sinifyoklama.network.model.Lesson
import com.kadirdogan97.sinifyoklama.network.model.Teacher

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsItemViewHolder>(){

    private val lessonList = arrayListOf<Lesson>()
    private var onItemClickListener : OnItemClickListener?=null
    private var isTeacher: Boolean=false

    fun setTeacher(isT: Boolean){
        this.isTeacher=isT
    }
    fun setLessonList(lessonList: List<Lesson>) {
        this.lessonList.clear()
        this.lessonList.addAll(lessonList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsItemViewHolder =
        LessonsItemViewHolder.create(
            parent
        )

    override fun getItemCount(): Int = lessonList.size

    override fun onBindViewHolder(holder: LessonsItemViewHolder, position: Int){
        holder.bind(lessonList[position],onItemClickListener!!, isTeacher)
    }


    class LessonsItemViewHolder(private val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(lesson: Lesson,clickListener: OnItemClickListener, isTeacher: Boolean){
            binding.nameLesson.text = lesson.ders_adi
            binding.ogrGorevliLesson.text = lesson.ogr_gorevli_ad_soyad
            if(lesson.yoklama_aktif.equals("1")){
                binding.yoklamaAktif.setBackgroundResource(R.color.colorGreen)
            }else{
                binding.yoklamaAktif.setBackgroundResource(R.color.colorRed)
            }
            if(isTeacher){
                binding.ogrGorevliLesson.visibility=View.GONE
            }
            itemView.setOnClickListener{
                clickListener.onItemClick(lesson)
            }
        }
        companion object{
            fun create(parent: ViewGroup): LessonsItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemLessonBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_lesson,
                    parent,
                    false
                )
                return LessonsItemViewHolder(
                    binding
                )
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(lesson: Lesson)
    }
}