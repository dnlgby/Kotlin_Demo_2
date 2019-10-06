package com.example.kotlin_ex2.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_ex2.databinding.ActivityMainGroupListItemBinding
import com.example.kotlin_ex2.databinding.ActivityMainGroupListNetworkStatusItemBinding
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.network.main.RequestStatus
import com.example.kotlin_ex2.network.main.Status

class MainRecyclerAdapter(private val retryCallback: () -> Unit) :
    PagedListAdapter<WhatsappGroup, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<WhatsappGroup>(DIFF_CALLBACK).build()
    ) {

    companion object {
        private const val GROUP_VIEWTYPE = 0
        private const val NETWORKSTATUS_VIEWTYPE = 1
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WhatsappGroup>() {
            override fun areItemsTheSame(oldItem: WhatsappGroup, newItem: WhatsappGroup) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: WhatsappGroup,
                newItem: WhatsappGroup
            ) = oldItem == newItem
        }
    }

    private var requestStatus: RequestStatus? = null


    fun setRequestStatus(newRequestStatus: RequestStatus?) {
        val previousState = this.requestStatus
        val hadExtraRow = hasExtraRow()
        this.requestStatus = newRequestStatus
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow) {// && previousState != newRequestStatus) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow(): Boolean {
        return requestStatus?.status in arrayOf(Status.FETCHING, Status.FAILED)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + (if (hasExtraRow()) 1 else 0)
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) NETWORKSTATUS_VIEWTYPE
        else GROUP_VIEWTYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GROUP_VIEWTYPE -> MainViewHolder.from(parent)
            NETWORKSTATUS_VIEWTYPE -> NetworkStatusViewHolder.from(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            GROUP_VIEWTYPE -> (holder as MainViewHolder).bind(getItem(position))
            NETWORKSTATUS_VIEWTYPE -> (holder as NetworkStatusViewHolder).bind(requestStatus)
        }
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

        fun bind(item: WhatsappGroup?) {
            binding.whatsappGroup = item
        }
    }


    class NetworkStatusViewHolder private constructor(
        private val binding: ActivityMainGroupListNetworkStatusItemBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup, retryCallback: () -> Unit): NetworkStatusViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    ActivityMainGroupListNetworkStatusItemBinding.inflate(inflater, parent, false)
                return NetworkStatusViewHolder(binding, retryCallback)
            }
        }

        fun bind(requestStatus: RequestStatus?) {
            binding.requestStatus = requestStatus
            binding.retryCallback = View.OnClickListener {
                retryCallback()
            }
        }

    }


}