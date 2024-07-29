package com.example.finalproject.ui.leftbar.fragments.images.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.images.state.ImagesState
import kotlinx.coroutines.launch

class ImagesViewModel(private val repository: LeftbarRepository) : ViewModel() {

    private val _state = MutableLiveData<ImagesState>()
    val state: LiveData<ImagesState> = _state

    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            _state.value = ImagesState.Loading
            try {
                val response = repository.getProductById(productId)

                if (response.isSuccessful) {
                    response.body()?.let { product ->
                        _state.value = ImagesState.Success(product)
                    } ?: run {
                        _state.value = ImagesState.Error("No se encontraron datos")
                    }
                } else {
                    _state.value = ImagesState.Error("Error del servidor: ${response.message()}")
                }
            } catch (e: Exception) {
                _state.value = ImagesState.Error("Error al cargar el producto: ${e.message}")
            }
        }
    }
}