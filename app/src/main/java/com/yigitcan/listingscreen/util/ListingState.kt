package com.yigitcan.listingscreen.util

import com.yigitcan.listingscreen.model.ItemModel

data class ListingState(
    val isLoading : Boolean = false,
    val list : List<ItemModel> = emptyList(),
    val error : String = "",
    )
