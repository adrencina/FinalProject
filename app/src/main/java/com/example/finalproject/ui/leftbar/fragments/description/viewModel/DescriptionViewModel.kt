package com.example.finalproject.ui.leftbar.fragments.description.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import kotlinx.coroutines.launch


class DescriptionViewModel(private val repository: LeftbarRepository ) : ViewModel() {

    private val _descriptionState = MutableLiveData<DescriptionState>()
    val descriptionState: LiveData<DescriptionState> = _descriptionState

    fun fetchProductById(productId: Int) {
        _descriptionState.value = DescriptionState.Loading

        viewModelScope.launch {
            try {
                val response = repository.getProductById(productId)
                Log.i("ddd", response.body().toString())
                if (response.isSuccessful) {
                    response.body()?.let { product ->
                        _descriptionState.value = DescriptionState.Success(product)
                    } ?: run {
                        _descriptionState.value = DescriptionState.Error("No product found")
                    }
                } else {
                    _descriptionState.value = DescriptionState.Error("Error fetching products")
                }
            } catch (e: Exception) {
                _descriptionState.value = DescriptionState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

}