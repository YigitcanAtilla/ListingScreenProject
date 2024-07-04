package com.yigitcan.listingscreen.util

import androidx.fragment.app.FragmentManager
import com.yigitcan.listingscreen.adapter.OnItemClickListener
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.view.DetailBottomSheetDialogFragment

class ItemClickListener(private val fragmentManager: FragmentManager) : OnItemClickListener {
    override fun onItemClick(item: ItemModel, position: Int) {
        val bottomSheetFragment = DetailBottomSheetDialogFragment.newInstance(item)
        bottomSheetFragment.show(fragmentManager, "detail_bottom_sheet")
    }
}