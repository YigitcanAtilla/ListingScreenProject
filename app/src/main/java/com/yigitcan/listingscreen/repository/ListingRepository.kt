package com.yigitcan.listingscreen.repository

import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.model.ListApiResponse

interface ListingRepository {

    suspend fun getList() : ListApiResponse

    suspend fun deleteItem(id: Int, list : List<ItemModel>) : List<ItemModel>

    suspend fun updateItem(itemModel: ItemModel, list : List<ItemModel>) : List<ItemModel>

}