package com.example.finalproject.ui.leftbar.fragments.description.viewModel

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

    // Función para obtener un producto por su id
    fun fetchProductById(idProduct: Int) {
        viewModelScope.launch {
            val result = repository.getProductById(idProduct)
            _descriptionState.postValue(DescriptionState.Success(result))
        }
    }
}