package com.afume.afume_android.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afume.afume_android.R
import com.afume.afume_android.data.vo.response.KeywordInfo
import com.afume.afume_android.databinding.RvItemFilterFlexboxBinding

class FlexboxRecyclerViewAdapter(internal val select:(KeywordInfo, Boolean)->Unit, val countBadge:(Int,Boolean)->Unit) :
    ListAdapter<KeywordInfo, FlexboxRecyclerViewAdapter.FlexboxRecyclerViewHolder>(
        incenseSeriesDiffCallback
    ) {
    init {
        setHasStableIds(true)
    }

    var data = mutableListOf<KeywordInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlexboxRecyclerViewHolder {
        val binding =
            RvItemFilterFlexboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlexboxRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: FlexboxRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }


    internal fun setData(data: MutableList<KeywordInfo>?){
        if(data!=null) this.data=data
        submitList(data)
        notifyDataSetChanged()

    }


    inner class FlexboxRecyclerViewHolder(val binding: RvItemFilterFlexboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KeywordInfo) {
            binding.rvData = data

            if(data.checked){
                binding.rvItemTxtFlexbox.apply {
                    setBackgroundColor(ContextCompat.getColor(this.context, R.color.point_beige))
                    setTextColor(ContextCompat.getColor(this.context, R.color.white))
                }
            }

            binding.root.setOnClickListener {
                if(data.clickable) {
                    if (!data.checked) {
                        binding.rvItemTxtFlexbox.apply {
                            setBackgroundColor(ContextCompat.getColor(this.context, R.color.point_beige))
                            setTextColor(ContextCompat.getColor(this.context, R.color.white))
                            select(data, true)
                            countBadge(2, true)
                            data.checked = true

                        }
                    } else {
                        binding.rvItemTxtFlexbox.apply {
                            background = ContextCompat.getDrawable(
                                this.context,
                                R.drawable.border_gray_cd_line_square
                            )
                            setTextColor(ContextCompat.getColor(this.context, R.color.gray_cd))
                            select(data, false)
                            countBadge(2, false)
                            data.checked = false
                        }

                    }
                }
                else{
                    Toast.makeText(it.context, "5개 이상 선택 할 수 없어요.", Toast.LENGTH_SHORT).show()
                }

            }
        }

        fun getItemDetails(viewHolder: RecyclerView.ViewHolder?): ItemDetailsLookup.ItemDetails<Long> {
            return object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getSelectionKey(): Long? {
                    return itemId
                }

                override fun getPosition(): Int {
                    if (viewHolder == null) {
                        return RecyclerView.NO_POSITION
                    }
                    return viewHolder.adapterPosition
                }

            }
        }
    }
}

val incenseSeriesDiffCallback = object : DiffUtil.ItemCallback<KeywordInfo>() {
    override fun areItemsTheSame(oldItem: KeywordInfo, newItem: KeywordInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: KeywordInfo, newItem: KeywordInfo): Boolean {
        return oldItem.name == newItem.name
    }

}