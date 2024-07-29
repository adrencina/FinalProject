package com.example.finalproject.ui.leftbar.fragments.description.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DescriptionViewModel(private val repository: LeftbarRepository ) : ViewModel() {

    private val _descriptionState = MutableLiveData<DescriptionState>()
    val descriptionState: LiveData<DescriptionState> = _descriptionState

    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getProductById(productId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _descriptionState.value = DescriptionState.Success(it)
                    } ?: run {
                        _descriptionState.value = DescriptionState.Error("No data found")
                    }
                } else {
                    _descriptionState.value = DescriptionState.Error("Server error: ${response.message()}")
                }
            } catch (e: HttpException) {
                _descriptionState.value = DescriptionState.Error("HTTP error: ${e.message()}")
            } catch (e: Exception) {
                _descriptionState.value = DescriptionState.Error("Unexpected error: ${e.message}")
            }
        }
    }

}