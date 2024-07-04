package com.yigitcan.listingscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null
) : Parcelable
