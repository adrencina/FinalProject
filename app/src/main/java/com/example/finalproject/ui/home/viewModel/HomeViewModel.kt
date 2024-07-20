package com.example.finalproject.ui.home.viewModel

import android.util.Log
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

    // LD último producto visitado
    private val _lastVisitedProduct = MutableLiveData<Product>()
    val lastVisitedProduct: LiveData<Product> get() = _lastVisitedProduct

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
                    response.body()?.let { productTypeResponse ->
                        _productTypes.postValue(productTypeResponse.productTypes)
                    } ?: run {
                        _error.postValue("Respuesta vacía")
                    }
                } else {
                    Log.e("HomeView", "Error: ${response.errorBody()?.string()}")
                    _error.postValue("${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error de red: ${e.message}")
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
                val response =
                    repository.getProducts(idProductType, productName, onlyFavorite, page, size)
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse != null) {
                        _products.postValue(productResponse.products)
                        Log.d("HomeViewModel", "Productos obtenidos: ${productResponse.products}")
                        productResponse.products.forEach { product ->
                            Log.d("HomeViewModel", "Producto: ${product.name}, Imagen URL: ${product.image}")
                        }
                    } else {
                        _error.postValue("Respuesta vacía del servidor")
                    }
                } else {
                    _error.postValue(
                        "Error al obtener productos: ${
                            response.errorBody()?.string()
                        }"
                    )
                    Log.e("HomeViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
                Log.e("HomeViewModel", "Error de red: ${e.message}")
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

    fun fetchLastVisitedProduct(productId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getProductDetails(productId)
                if (response.isSuccessful) {
                    _lastVisitedProduct.postValue(response.body())
                } else {
                    _error.postValue("Error al obtener el producto visitado")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
            }
        }
    }

}