package com.yigitcan.listingscreen.api

import com.yigitcan.listingscreen.model.ListApiResponse
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/posts")
    suspend fun getList() : ListApiResponse

}