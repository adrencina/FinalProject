package com.example.finalproject.ui.search.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    // LD lista de productos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // LD manejar errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // LD último producto visitado
    private val _lastVisitedProduct = MutableLiveData<Product>()
    val lastVisitedProduct: LiveData<Product> get() = _lastVisitedProduct

    // LD detalles del producto
    private val _productDetails = MutableLiveData<Product>()
    val productDetails: LiveData<Product> get() = _productDetails

    private val _searchResult = MutableLiveData<Boolean>()
    val searchResult: LiveData<Boolean> get() = _searchResult

    private val _typefetch = ""
    val typefetch = _typefetch

    fun fetchLastVisitedProduct(productId: Int) {


    }

    // Fun obtener productos con filtros opcionales
    fun fetchProductsSearch(
        idProductType: Int? = null,
        productName: String? = null,
        onlyFavorite: Boolean? = null,
        page: Int?=null,
        size: Int?=null
    ) {
        viewModelScope.launch {
            try {
                val response =
                    repository.getProductsSearch(idProductType, productName, onlyFavorite, page, size)
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse != null) {
                        _products.postValue(productResponse.products)
                        Log.d("HomeViewModel", "Productos obtenidos: ${productResponse.products}")
                        productResponse.products.forEach { product ->
                            Log.d(
                                "HomeViewModel",
                                "Producto: ${product.name}, Imagen URL: ${product.image}"
                            )
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

}
