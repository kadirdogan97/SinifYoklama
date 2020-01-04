package com.kadirdogan97.sinifyoklama.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ItemDiscontinuityBinding
import com.kadirdogan97.sinifyoklama.databinding.ItemDiscontinuityTBinding
import com.kadirdogan97.sinifyoklama.databinding.ItemLessonBinding
import com.kadirdogan97.sinifyoklama.network.model.Discontinuity
import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService
import com.kadirdogan97.sinifyoklama.network.model.Lesson

class DiscontinuityTAdapter : RecyclerView.Adapter<DiscontinuityTAdapter.DiscontinuityItemViewHolder>() {

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

    class DiscontinuityItemViewHolder(private val binding: ItemDiscontinuityTBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(discontinuity: Discontinuity) {
            binding.ogrAdSoyadT.text = discontinuity.ogr_ad_soyad
            binding.ogrNoT.text = discontinuity.ogr_no
            binding.devamsizlikSayiT.text = "Devamsızlık: "+discontinuity.devamsizlik

        }

        companion object {
            fun create(parent: ViewGroup): DiscontinuityItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemDiscontinuityTBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_discontinuity_t,
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