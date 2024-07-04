package com.yigitcan.listingscreen.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.yigitcan.listingscreen.adapter.ListingRecyclerViewAdapter
import com.yigitcan.listingscreen.viewmodel.ListingViewModel


class SwipeToDeleteCallback(private val adapter: ListingRecyclerViewAdapter, private val viewModel : ListingViewModel) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val itemModel = adapter.getItemModel(position)
        itemModel.id?.let { viewModel.deleteItem(it) }
    }
}
