package com.example.finalproject.ui.leftbar.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class sharedViewModel : ViewModel() {

    private val _productPrice = MutableLiveData<String>()
    val productPrice: LiveData<String> get() = _productPrice

    private val _productId = MutableLiveData<Int>()
    val productId: LiveData<Int> get() = _productId

    private val _fragmentTittle = MutableLiveData<String>()
    val fragmentTittle: LiveData<String> get() = _fragmentTittle


    fun productIdvalue(new: Int) {
        _productId.value = new
    }

    fun productPricevalue(new: String) {
        _productPrice.value = new
    }

    fun fragmentTittle(new: String) {
        _fragmentTittle.value = new
    }
}