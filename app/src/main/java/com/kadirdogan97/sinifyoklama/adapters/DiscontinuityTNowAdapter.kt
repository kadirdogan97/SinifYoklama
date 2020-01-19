package com.kadirdogan97.sinifyoklama.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.databinding.ItemDiscontinuityTNowBinding
import com.kadirdogan97.sinifyoklama.network.model.Discontinuity

class DiscontinuityTNowAdapter : RecyclerView.Adapter<DiscontinuityTNowAdapter.DiscontinuityItemViewHolder>() {

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

    class DiscontinuityItemViewHolder(private val binding: ItemDiscontinuityTNowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(discontinuity: Discontinuity) {
            binding.ogrAdSoyadT.text = discontinuity.ogr_ad_soyad
            binding.ogrNoT.text = discontinuity.ogr_no
            if(discontinuity.devamsizlikSuan.equals("1")){
                binding.statusDiscontinuity.setBackgroundResource(R.color.colorGreen)
            }else{
                binding.statusDiscontinuity.setBackgroundResource(R.color.colorRed)
            }

        }

        companion object {
            fun create(parent: ViewGroup): DiscontinuityItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemDiscontinuityTNowBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_discontinuity_t_now,
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