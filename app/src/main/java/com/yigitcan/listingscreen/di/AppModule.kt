package com.yigitcan.listingscreen.di

import com.yigitcan.listingscreen.api.RetrofitAPI
import com.yigitcan.listingscreen.repository.ListingRepository
import com.yigitcan.listingscreen.repository.ListingRepositoryImpl
import com.yigitcan.listingscreen.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitAPI() : RetrofitAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideListingRepository(retrofitAPI: RetrofitAPI) : ListingRepository {
        return ListingRepositoryImpl(retrofitAPI)
    }
}
