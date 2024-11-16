package com.shuvo6904.gozayaan.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.databinding.ItemRecommendedHomeBinding
import com.shuvo6904.gozayaan.utils.Extensions.loadImage

class RecommendedAdapter(
    private val onItemClicked: (RecommendedItem) -> Unit
) : ListAdapter<RecommendedItem, RecommendedAdapter.RecommendedViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecommendedItem>() {
            override fun areItemsTheSame(
                oldItem: RecommendedItem,
                newItem: RecommendedItem
            ): Boolean {
                return oldItem.propertyName == newItem.propertyName
            }

            override fun areContentsTheSame(
                oldItem: RecommendedItem,
                newItem: RecommendedItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class RecommendedViewHolder private constructor(
        private val binding: ItemRecommendedHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecommendedItem, onItemClicked: (RecommendedItem) -> Unit) {
            binding.apply {
                recommendedImage.loadImage(item.heroImage)
                propertyName.text = item.propertyName
                location.text = item.location

                root.setOnClickListener {
                    onItemClicked(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecommendedViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecommendedHomeBinding.inflate(layoutInflater, parent, false)
                return RecommendedViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        return RecommendedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}