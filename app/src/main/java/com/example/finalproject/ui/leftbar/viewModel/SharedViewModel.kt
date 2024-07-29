package com.example.finalproject.ui.leftbar.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _productPrice = MutableLiveData<String>()
    val productPrice: LiveData<String> get()=_productPrice

    private val _productId = MutableLiveData<Int>()
    val productId: LiveData<Int> get()=_productId

    fun productIdvalue (new:Int){

        _productId.value = new
    }

    fun productPricevalue (new:String){

        _productPrice.value = new
    }
}