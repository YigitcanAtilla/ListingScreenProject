package com.yigitcan.listingscreen.usecase

import coil.network.HttpException
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.repository.ListingRepository
import com.yigitcan.listingscreen.util.Constants
import com.yigitcan.listingscreen.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class UpdateItemUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {

    fun updateItem(itemModel : ItemModel, list : List<ItemModel>) : Flow<Resource<List<ItemModel>>> = flow {
        try {
            emit(Resource.Loading())
            val updatedList = listingRepository.updateItem(itemModel,list)
            emit(Resource.Success(updatedList))
        }  catch (e : IOError) {
            emit(Resource.Error(Constants.NO_INTERNET_MESSAGE))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.ERROR_MESSAGE))
        }
    }

}