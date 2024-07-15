package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.dto.request.FavoriteProductRequest
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.dto.response.Product
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    // LD lista de productos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // LD lista de tipos de productos
    private val _productTypes = MutableLiveData<List<ProductType>>()
    val productTypes: LiveData<List<ProductType>> get() = _productTypes

    // LD oferta diaria
    private val _dailyOffer = MutableLiveData<Product>()
    val dailyOffer: LiveData<Product> get() = _dailyOffer

    // LD detalles del producto
    private val _productDetails = MutableLiveData<Product>()
    val productDetails: LiveData<Product> get() = _productDetails

    // LD manejar errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Fun obtener categorías de productos
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getProductTypes()
                if (response.isSuccessful) {
                    response.body()?.let { productTypesList ->
                        _productTypes.postValue(productTypesList)
                    } ?: run {
                        _error.postValue("Error al obtener categorías")
                    }
                } else {
                    _error.postValue("Error al obtener categorías")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
            }
        }
    }

    // Fun obtener productos con filtros opcionales
    fun fetchProducts(
        idProductType: Int? = null,
        productName: String? = null,
        onlyFavorite: Boolean = false,
        page: Int = 1,
        size: Int = 10
    ) {
        viewModelScope.launch {
            try {
                val response = repository.getProducts(idProductType, productName, onlyFavorite, page, size)
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    _products.postValue(productResponse?.products ?: emptyList())
                } else {
                    _error.postValue("Error al obtener productos")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
            }
        }
    }


    // Fun obtener el producto destacado del día
    fun fetchFeaturedProduct() {
        viewModelScope.launch {
            try {
                val response = repository.updateDailyOffer(FavoriteProductRequest(idProduct = 0))
                if (response.isSuccessful) {
                    _dailyOffer.postValue(response.body())
                } else {
                    _error.postValue("Error al obtener la oferta diaria")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
            }
        }
    }
}