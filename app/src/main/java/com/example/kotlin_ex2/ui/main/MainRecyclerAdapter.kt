package com.example.kotlin_ex2.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_ex2.databinding.ActivityMainGroupListItemBinding
import com.example.kotlin_ex2.domain.WhatsappGroup

//private val clickListener: MainListClickListener
class MainRecyclerAdapter :
    PagedListAdapter<WhatsappGroup, MainRecyclerAdapter.MainViewHolder>(
        AsyncDifferConfig.Builder<WhatsappGroup>(DIFF_CALLBACK).build()
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WhatsappGroup>() {
            override fun areItemsTheSame(oldItem: WhatsappGroup, newItem: WhatsappGroup): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: WhatsappGroup,
                newItem: WhatsappGroup
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position)!!)//, clickListener)
    }

    class MainViewHolder private constructor(private val binding: ActivityMainGroupListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ActivityMainGroupListItemBinding.inflate(inflater, parent, false)
                return MainViewHolder(binding)
            }
        }

        fun bind(item: WhatsappGroup) {//}, clickListener: MainListClickListener) {
            binding.whatsappGroup = item
            //binding.clickListener = clickListener
        }
    }

//    //Main item click listener
//    class MainListClickListener(val clickListener: (WhatsappGroup) -> Unit) {
//        fun onClick(todo: WhatsappGroup) = clickListener(todo)
//    }

}