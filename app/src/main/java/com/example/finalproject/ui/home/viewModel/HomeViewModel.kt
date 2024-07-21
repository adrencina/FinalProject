package com.example.finalproject.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.service.dto.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    // LD lista de productos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // LD lista de tipos de productos
    private val _productTypes = MutableLiveData<List<ProductType>>()
    val productTypes: LiveData<List<ProductType>> get() = _productTypes

    // LD oferta diaria
    private val _dailyOffer = MutableLiveData<DailyOfferResponse>()
    val dailyOffer: LiveData<DailyOfferResponse> get() = _dailyOffer

    // LD último producto visitado
    private val _lastVisitedProduct = MutableLiveData<Product>()
    val lastVisitedProduct: LiveData<Product> get() = _lastVisitedProduct

    // LD detalles del producto
    private val _productDetails = MutableLiveData<Product>()
    val productDetails: LiveData<Product> get() = _productDetails
    private val _searchResult = MutableLiveData<Boolean>()
    val searchResult: LiveData<Boolean> get() = _searchResult

    // LD manejar errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Fun obtener categorías de productos
    private val _favorites = MutableLiveData<List<Product>>()
    val favorites: LiveData<List<Product>> get() = _favorites

    private val _homeState = MutableLiveData<HomeState>()
    val homeState : LiveData<HomeState> = _homeState

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

    // Fun obtener el producto destacado del día
    fun fetchFeaturedProduct() {
        viewModelScope.launch {
            try {
                val response = repository.getDailyOffer()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _dailyOffer.postValue(it)
                    } ?: run {
                        _error.postValue("Respuesta vacía del servidor")
                    }
                } else {
                    _error.postValue("Error al obtener la oferta diaria")
                    Log.e("HomeViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de red: ${e.message}")
                Log.e("HomeViewModel", "Error de red: ${e.message}")
            }
        }
    }

    // Fun para marcar un producto como ultimo visitado
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
    fun searchViewController(
        result: Boolean,
    ) {
        if (result) {
            _searchResult.postValue(true)
        } else {
            _searchResult.postValue(false)
        }
    }
            }
        }
    }

//  todo esto va con el pronto consumo al endpoint de favorite

//    private suspend fun addFavoritesProduct(id:Int) {
//        viewModelScope.launch {
//            _homeState.postValue(HomeState.Loading)
//            val addFavoritesResponse = homeRepository.addFavoritesProduct(id)
//            if (addFavoritesResponse.isSuccessful){
//                addFavoritesResponse.body()?.let {
//                    _homeState.postValue(HomeState.Success(it))
//                }?: _homeState.postValue(HomeState.Error("Error en el servicio"))
//            }else{
//                _homeState.postValue(HomeState.Error("Error en el servidor"))
//            }
//
//
//        }
//    }