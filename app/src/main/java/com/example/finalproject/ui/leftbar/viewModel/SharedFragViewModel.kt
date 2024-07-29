package com.example.finalproject.ui.leftbar.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedFragViewModel : ViewModel() {

    private val _productPrice = MutableLiveData<String>()
    val productPrice: LiveData<String> get() = _productPrice

    private val _productId = MutableLiveData<Int>()
    val productId: LiveData<Int> get() = _productId

    private val _fragmentTittle = MutableLiveData<String>()
    val fragmentTittle: LiveData<String> get() = _fragmentTittle

    private val _productName = MutableLiveData<String>()
    val productName: LiveData<String> get() = _productName

    private val _productCurrency = MutableLiveData<String>()
    val productCurrency: LiveData<String> get() = _productCurrency


    fun productIdvalue(new: Int) {
        _productId.value = new
    }

    fun productPricevalue(new: String) {
        _productPrice.value = new
    }

    fun fragmentTittle(new: String) {
        _fragmentTittle.value = new
    }
    fun productName(new: String) {
        _productName.value = new
    }

    fun productCurrency(new: String) {
        _productCurrency.value = new
    }
}