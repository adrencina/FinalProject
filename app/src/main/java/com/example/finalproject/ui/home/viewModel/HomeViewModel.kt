package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.Category
import com.example.finalproject.data.service.dto.HomeState
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

    private val _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> get() = _homeState

    // obtener categorías
    fun fetchCategories() {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = homeRepository.getCategories()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _categories.postValue(it)
                    _homeState.value = HomeState.Success("Categorías cargadas exitosamente")
                } ?: run {
                    _error.postValue("Error al cargar categorías")
                    _homeState.value = HomeState.Error("Error al cargar categorías")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message ?: "Error desconocido")
                _homeState.value = HomeState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    // obtener productos por categoría
    fun fetchProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = homeRepository.getProductsByCategory(categoryId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                    _homeState.value = HomeState.Success("Productos cargados exitosamente")
                } ?: run {
                    _error.postValue("Error al cargar productos para la categoría")
                    _homeState.value = HomeState.Error("Error al cargar productos para la categoría")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message ?: "Error desconocido")
                _homeState.value = HomeState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
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
            _homeState.value = HomeState.Loading
            val result = homeRepository.searchProducts(query, categoryId, color, size, gender)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _products.postValue(it)
                    _homeState.value = HomeState.Success("Productos buscados exitosamente")
                } ?: run {
                    _error.postValue("Error al buscar productos")
                    _homeState.value = HomeState.Error("Error al buscar productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message ?: "Error desconocido")
                _homeState.value = HomeState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    // obtener ofertas
    fun fetchOnSaleProducts() {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = homeRepository.getOnSaleProducts()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _onSaleProducts.postValue(it)
                    _homeState.value = HomeState.Success("Productos en oferta cargados exitosamente")
                } ?: run {
                    _error.postValue("Error al cargar productos en oferta")
                    _homeState.value = HomeState.Error("Error al cargar productos en oferta")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message ?: "Error desconocido")
                _homeState.value = HomeState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
}