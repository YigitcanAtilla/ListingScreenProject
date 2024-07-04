package com.yigitcan.listingscreen.model

class ListApiResponse : ArrayList<ListApiResponseItem>()

fun ListApiResponse.mapToItemModelList() : List<ItemModel> {
    return map {
    ItemModel(
        description = it.body,
        id = it.id,
        title = it.title)
    }
}