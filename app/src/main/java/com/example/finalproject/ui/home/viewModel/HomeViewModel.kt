package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeRepository = HomeRepository(HomeApiServiceImp())

    // Liv. cat. productos
    private val _categories = MutableLiveData<List<ProductType>>()
    val categories: LiveData<List<ProductType>> get() = _categories

    // Liv. productos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // Liv. ofertas
    private val _onSaleProducts = MutableLiveData<List<DailyOfferResponse>>()
    val onSaleProducts: LiveData<List<DailyOfferResponse>> get() = _onSaleProducts

    // Liv. último producto
    private val _lastUserProduct = MutableLiveData<Product>()
    val lastUserProduct: LiveData<Product> get() = _lastUserProduct

    // Liv. errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

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

    fun fetchProducts() {
        viewModelScope.launch {
            val result = homeRepository.getProducts()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                } ?: run {
                    _error.postValue("No se pudieron obtener los productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

//
//    fun fetchOnSaleProducts() {
//        viewModelScope.launch {
//            val result = homeRepository.getOnSaleProducts()
//            if (result.isSuccess) {
//                result.getOrNull()?.let {
//                    _onSaleProducts.postValue(it)
//                } ?: run {
//                    _error.postValue("No se pudieron obtener los productos en oferta")
//                }
//            } else {
//                _error.postValue(result.exceptionOrNull()?.message)
//            }
//        }
//    }

    fun fetchLastUserProduct() {
        viewModelScope.launch {
            val result = homeRepository.getLastUserProduct()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _lastUserProduct.postValue(it)
                } ?: run {
                    _error.postValue("No se pudo obtener el último producto del usuario")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }
}