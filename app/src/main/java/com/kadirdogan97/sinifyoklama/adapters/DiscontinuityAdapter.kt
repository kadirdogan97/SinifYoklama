package com.kadirdogan97.sinifyoklama.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ItemDiscontinuityBinding
import com.kadirdogan97.sinifyoklama.databinding.ItemLessonBinding
import com.kadirdogan97.sinifyoklama.network.model.Discontinuity
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService
import com.kadirdogan97.sinifyoklama.network.model.Lesson

class DiscontinuityAdapter : RecyclerView.Adapter<DiscontinuityAdapter.DiscontinuityItemViewHolder>() {

    private val discontinuityList = arrayListOf<Discontinuity>()
    fun setDiscontinuityList(discontinuityList: List<Discontinuity>) {
        this.discontinuityList.clear()
        this.discontinuityList.addAll(discontinuityList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscontinuityItemViewHolder =
        DiscontinuityItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: DiscontinuityItemViewHolder, position: Int) {
        holder.bind(discontinuityList[position])
    }

    override fun getItemCount(): Int = discontinuityList.size

    class DiscontinuityItemViewHolder(private val binding: ItemDiscontinuityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(discontinuity: Discontinuity) {
            binding.dateDiscontinuity.text = discontinuity.yoklama_tarih
        }

        companion object {
            fun create(parent: ViewGroup): DiscontinuityItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemDiscontinuityBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_discontinuity,
                    parent,
                    false
                )
                return DiscontinuityItemViewHolder(
                    binding
                )
            }
        }
    }
}