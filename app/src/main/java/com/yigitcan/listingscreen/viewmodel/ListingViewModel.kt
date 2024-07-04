package com.yigitcan.listingscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.usecase.DeleteItemUseCase
import com.yigitcan.listingscreen.usecase.GetListingUseCase
import com.yigitcan.listingscreen.usecase.UpdateItemUseCase
import com.yigitcan.listingscreen.util.Constants
import com.yigitcan.listingscreen.util.ListingState
import com.yigitcan.listingscreen.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val getListingUseCase: GetListingUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
    ) : ViewModel() {

    private val _state = MutableStateFlow(ListingState())
    val state: StateFlow<ListingState> = _state.asStateFlow()

    private var job : Job? = null

    init {
        getList()
    }

    private fun getList() {
        job?.cancel()

        job = getListingUseCase.executeGetList()
            .flowOn(Dispatchers.IO)
            .onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = it.message ?: Constants.ERROR_MESSAGE, isLoading = false)
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(list = it.data ?: emptyList(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateItem(item: ItemModel){
        job?.cancel()

        job = updateItemUseCase.updateItem(item,state.value.list)
            .flowOn(Dispatchers.Main)
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = it.message ?: Constants.ERROR_MESSAGE, isLoading = false)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(list = it.data ?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun deleteItem(id: Int){
        job?.cancel()

        job = deleteItemUseCase.deleteItem(id, state.value.list).flowOn(Dispatchers.Main).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = it.message ?: Constants.ERROR_MESSAGE, isLoading = false)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(list = it.data ?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}