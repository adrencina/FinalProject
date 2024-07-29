package com.example.finalproject.ui.similar.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.SimilarRepository
import kotlinx.coroutines.launch

class SimilarViewModel(private val repository: SimilarRepository) : ViewModel() {

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


    // Fun obtener productos con filtros opcionales
    fun fetchProductsSimilar(
        idProduct: Int,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    repository.getSimilarProducts(idProduct)
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse != null) {
                        _products.postValue(productResponse.products)
                        Log.d("HomeViewModel", "Productos obtenidos: ${productResponse.products}")
                        productResponse.products.forEach { product ->
                            Log.d(
                                "HomeViewModel",
                                "Producto: ${product.name}, Imagen URL: ${product.images}"
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
