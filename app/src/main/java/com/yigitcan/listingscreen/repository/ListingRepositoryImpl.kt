package com.yigitcan.listingscreen.repository

import com.yigitcan.listingscreen.api.RetrofitAPI
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.model.ListApiResponse
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val listingApi: RetrofitAPI
): ListingRepository {
    override suspend fun getList(): ListApiResponse {
        return listingApi.getList()
    }

    override suspend fun deleteItem(id: Int,list : List<ItemModel>) : List<ItemModel> {
        val mutableCopy = list.toMutableList()
        mutableCopy.removeIf { it.id == id }
        return mutableCopy
    }

    override suspend fun updateItem(itemModel: ItemModel,list : List<ItemModel>) : List<ItemModel> {
        val mutableCopy = list.toMutableList()
        val indexToUpdate = mutableCopy.indexOfFirst { it.id == itemModel.id }
        if (indexToUpdate != -1) {
            val updatedItem = mutableCopy[indexToUpdate].copy(
                title = itemModel.title,
                description = itemModel.description
            )
            mutableCopy[indexToUpdate] = updatedItem
        }
        return mutableCopy
    }

}