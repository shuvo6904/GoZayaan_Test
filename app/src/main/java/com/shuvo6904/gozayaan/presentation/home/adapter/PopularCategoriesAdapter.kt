package com.shuvo6904.gozayaan.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shuvo6904.gozayaan.data.model.PopularCategory
import com.shuvo6904.gozayaan.databinding.ItemPopularCategoriesBinding

class PopularCategoriesAdapter(
    private val onItemClicked: (PopularCategory) -> Unit
) : ListAdapter<PopularCategory, PopularCategoriesAdapter.PopularCategoriesViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularCategory>() {
            override fun areItemsTheSame(
                oldItem: PopularCategory,
                newItem: PopularCategory
            ): Boolean {
                return oldItem.iconResId == newItem.iconResId
            }

            override fun areContentsTheSame(
                oldItem: PopularCategory,
                newItem: PopularCategory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class PopularCategoriesViewHolder private constructor(
        private val binding: ItemPopularCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PopularCategory, onItemClicked: (PopularCategory) -> Unit) {
            binding.apply {
                categoryImage.setImageResource(item.iconResId)
                categoryName.text = item.name

                root.setOnClickListener {
                    onItemClicked(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): PopularCategoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPopularCategoriesBinding.inflate(layoutInflater, parent, false)
                return PopularCategoriesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCategoriesViewHolder {
        return PopularCategoriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PopularCategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}