package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.dto.response.Product
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeRepository: HomeRepository = HomeRepository(HomeApiServiceImp())

    private val _categories = MutableLiveData<List<ProductType>>()
    val categories: LiveData<List<ProductType>> get() = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _featuredProduct = MutableLiveData<Product?>()
    val featuredProduct: LiveData<Product?> get() = _featuredProduct

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _searchResult = MutableLiveData<Boolean>()
    val searchResult : LiveData<Boolean> get() = _searchResult

    private val _favorites = MutableLiveData<List<Product>>()
    val favorites: LiveData<List<Product>> get() = _favorites

    fun fetchCategories() {
        viewModelScope.launch {
            val result = homeRepository.getProductTypes()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _categories.postValue(it.productTypes)
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
                    _products.postValue(it.products)
                } ?: run {
                    _error.postValue("No se pudieron obtener los productos")
                }
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }

    fun fetchFeaturedProduct() {
        viewModelScope.launch {
            val lastUserProductResult = homeRepository.getLastUserProduct()
            val lastUserProductResponse = lastUserProductResult.getOrNull()

            val lastUserProduct = lastUserProductResponse?.let {
                Product(
                    idProduct = it.idProduct,
                    name = it.name,
                    productType = it.productType,
                    currency = it.currency,
                    price = it.price,
                    image = it.image,
                    isFavorite = it.isFavorite,
                    description = it.description
                )
            }

            if (lastUserProduct != null) {
                _featuredProduct.postValue(lastUserProduct)
            } else {
                fetchDailyOffer()
            }
        }
    }

    private suspend fun fetchDailyOffer() {
        val dailyOfferResult = homeRepository.getDailyOffer()
        if (dailyOfferResult.isSuccess) {
            val dailyOffer = dailyOfferResult.getOrNull()
            if (dailyOffer != null) {
                val product = Product(
                    idProduct = dailyOffer.idProduct,
                    name = dailyOffer.name,
                    productType = dailyOffer.productType,
                    currency = dailyOffer.currency,
                    price = dailyOffer.price,
                    image = dailyOffer.image,
                    isFavorite = dailyOffer.isFavorite,
                    description = dailyOffer.description
                )
                _featuredProduct.postValue(product)
            } else {
                _error.postValue("No se pudo obtener el producto destacado")
            }
        } else {
            _error.postValue("No se pudo obtener el producto destacado")
        }
    }

    fun searchViewController(
        result:Boolean,
    ){
        if (result){
            _searchResult.postValue(true)
        }else{
            _searchResult.postValue(false)
        }
    }
    //falta completar la corrutina
    suspend fun addFavorites(){
        viewModelScope.launch {


        }
    }
}
