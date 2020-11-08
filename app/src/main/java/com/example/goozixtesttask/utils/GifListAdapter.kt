package com.example.goozixtesttask.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.goozixtesttask.databinding.ListViewItemBinding
import com.example.goozixtesttask.network.Data
import kotlinx.android.synthetic.main.list_view_item.view.*

class GifListAdapter(private val onClickListener: OnClickListener) : ListAdapter<Data, GifListAdapter.DataViewHolder> (DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.share_button.setOnClickListener {
            onClickListener.onClick(data)
        }
        holder.bind(data)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class DataViewHolder(private val binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.data = data
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (data: Data) -> Unit) {
        fun onClick(data: Data) = clickListener(data)
    }
}
