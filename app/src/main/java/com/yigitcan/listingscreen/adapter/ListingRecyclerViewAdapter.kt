package com.yigitcan.listingscreen.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yigitcan.listingscreen.R
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.util.Constants

class ListingRecyclerViewAdapter : ListAdapter<ItemModel, ListingRecyclerViewAdapter.ViewHolder>(DiffCallback()) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = View.inflate(parent.context, R.layout.list_item_view, null)
        return ViewHolder(view,onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,position)
    }

    class ViewHolder(itemView: View,private val onItemClickListener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemModel,position: Int) {
            itemView.apply {
                findViewById<ImageView>(R.id.uiImage).load(Constants.IMAGE_URL.replace("{id}", item.id.toString())) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                }
                findViewById<TextView>(R.id.headerTitle).text = item.title
                findViewById<TextView>(R.id.description).text = item.description
                setOnClickListener {
                    onItemClickListener?.onItemClick(item,position)
                }
            }
        }
    }

    fun getItemModel(position: Int) : ItemModel {
        return getItem(position)
    }

    class DiffCallback : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(item: ItemModel, position: Int)
}