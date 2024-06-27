package com.example.finalproject.ui.home.viewModel

import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.service.dto.HomeState
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Utils.visible
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.Category
import com.example.finalproject.data.service.dto.Product
import com.example.finalproject.ui.home.recycler.productProvider
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> = _homeState

    private val homeRepository = HomeRepository(HomeApiServiceImp())

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _onSaleProducts = MutableLiveData<List<Product>>()
    val onSaleProducts: LiveData<List<Product>> get() = _onSaleProducts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _searchResult = MutableLiveData<Boolean>()
    val searchResult : LiveData<Boolean> get() = _searchResult



//    fun fetchCategories() {
//        _homeState.postValue(HomeState.Loading)
//        viewModelScope.launch {
//            val result = homeRepository.getCategories()
//            if (result.isSuccess) {
//                result.getOrNull()?.let {
//                    _homeState.postValue(HomeState.Success("Categorías cargadas con éxito"))
//                } ?: run {
//                    _homeState.postValue(HomeState.Error("Error al obtener categorías"))
//                }
//            } else {
//                _homeState.postValue(
//                    HomeState.Error(
//                        result.exceptionOrNull()?.message ?: "Error desconocido"
//                    )
//                )
//            }
//        }
//    }
//
//    // funcion para obtener productos por categoría
//    fun fetchProductsByCategory(categoryId: Int) {
//        _homeState.postValue(HomeState.Loading)
//        viewModelScope.launch {
//            val result = homeRepository.getProductsByCategory(categoryId)
//            if (result.isSuccess) {
//                result.getOrNull()?.let {
//                    _homeState.postValue(HomeState.Success("Productos cargados con éxito"))
//                } ?: run {
//                    _homeState.postValue(HomeState.Error("Error al obtener productos para la categoría"))
//                }
//            } else {
//                _homeState.postValue(
//                    HomeState.Error(
//                        result.exceptionOrNull()?.message ?: "Error desconocido"
//                    )
//                )
//            }
//        }
//    }
//
//    // funcion para buscar productos por criterios de búsqueda
//    fun searchProducts(
//        query: String?,
//        categoryId: Int?,
//        color: String?,
//        size: String?,
//        gender: String?
//    ) {
//        _homeState.postValue(HomeState.Loading)
//        viewModelScope.launch {
//            val result = homeRepository.searchProducts(query, categoryId, color, size, gender)
//            if (result.isSuccess) {
//                result.getOrNull()?.let {
//                    _homeState.postValue(HomeState.Success("Productos cargados con éxito"))
//                } ?: run {
//                    _homeState.postValue(HomeState.Error("Error al buscar productos"))
//                }
//            } else {
//                _homeState.postValue(
//                    HomeState.Error(
//                        result.exceptionOrNull()?.message ?: "Error desconocido"
//                    )
//                )
//            }
//        }
//    }
//
//    fun fetchOnSaleProducts() {
//        _homeState.postValue(HomeState.Loading)
//        viewModelScope.launch {
//            val result = homeRepository.getOnSaleProducts()
//            if (result.isSuccess) {
//                result.getOrNull()?.let {
//                    _homeState.postValue(HomeState.Success("Productos en oferta cargados con éxito"))
//                } ?: run {
//                    _homeState.postValue(HomeState.Error("Error al obtener productos en oferta"))
//                }
//            } else {
//                _homeState.postValue(
//                    HomeState.Error(
//                        result.exceptionOrNull()?.message ?: "Error desconocido"
//                    )
//                )
//            }
//        }
//    }


    fun searchViewController(
        result:Boolean,
    ){
        if (result == true){
            _searchResult.postValue(true)
        }else{
            _searchResult.postValue(false)
        }
    }


}
