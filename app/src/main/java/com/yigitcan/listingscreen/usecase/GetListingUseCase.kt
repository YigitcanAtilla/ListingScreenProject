package com.yigitcan.listingscreen.usecase

import coil.network.HttpException
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.model.mapToItemModelList
import com.yigitcan.listingscreen.repository.ListingRepository
import com.yigitcan.listingscreen.util.Constants
import com.yigitcan.listingscreen.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetListingUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    fun executeGetList() : Flow<Resource<List<ItemModel>>> = flow {
        try {
            emit(Resource.Loading())
            val itemModelList = listingRepository.getList()
            if(itemModelList.isNotEmpty()) {
                emit(Resource.Success(itemModelList.mapToItemModelList()))
            } else {
                emit(Resource.Error(Constants.EMPTY_LIST_MESSAGE))
            }
        } catch (e : IOError) {
            emit(Resource.Error(Constants.NO_INTERNET_MESSAGE))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.ERROR_MESSAGE))
        }
    }
}