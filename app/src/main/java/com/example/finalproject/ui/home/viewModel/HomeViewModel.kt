package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.Category
import com.example.finalproject.data.service.dto.Product
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository(HomeApiServiceImp())

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _onSaleProducts = MutableLiveData<List<Product>>()
    val onSaleProducts: LiveData<List<Product>> get() = _onSaleProducts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCategories() {
        viewModelScope.launch {
            val result = homeRepository.getCategories()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _categories.postValue(it)
                } ?: run {
                    _error.postValue("No se pudo obtener las categorías")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    // funcion para obtener productos por categoría
    fun fetchProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            val result = homeRepository.getProductsByCategory(categoryId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                } ?: run {
                    _error.postValue("No se pudo obtener los productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    // funcion para buscar productos por criterios de búsqueda
    fun searchProducts(
        query: String?,
        categoryId: Int?,
        color: String?,
        size: String?,
        gender: String?
    ) {
        viewModelScope.launch {
            val result = homeRepository.searchProducts(query, categoryId, color, size, gender)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                } ?: run {
                    _error.postValue("No se pudo buscar los productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    fun fetchOnSaleProducts() {
        viewModelScope.launch {
            val result = homeRepository.getOnSaleProducts()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _onSaleProducts.postValue(it)
                } ?: run {
                    _error.postValue("No se pudo obtener las oferta")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }
}
