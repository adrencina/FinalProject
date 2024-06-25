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

    // Liv. cat. productos
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    // Liv. productos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // Liv. ofertas
    private val _onSaleProducts = MutableLiveData<List<Product>>()
    val onSaleProducts: LiveData<List<Product>> get() = _onSaleProducts

    // Liv. errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // obtener categorías
    fun fetchCategories() {
        viewModelScope.launch {
            val result = homeRepository.getCategories()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _categories.postValue(it)
                } ?: run {
                    _error.postValue("No se pudieron obtener las categorías")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    // obtener productos por categoría
    fun fetchProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            val result = homeRepository.getProductsByCategory(categoryId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                } ?: run {
                    _error.postValue("No se pudieron obtener los productos para la categoría")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    // buscar productos
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
                    _error.postValue("No se pudieron buscar los productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    // obtener ofertas
    fun fetchOnSaleProducts() {
        viewModelScope.launch {
            val result = homeRepository.getOnSaleProducts()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _onSaleProducts.postValue(it)
                } ?: run {
                    _error.postValue("No se pudieron obtener los productos en oferta")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }
}

