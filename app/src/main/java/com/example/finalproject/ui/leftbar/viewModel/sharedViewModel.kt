package com.example.finalproject.ui.leftbar.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class sharedViewModel : ViewModel() {

    private val _product = MutableLiveData<String>()
    val product: LiveData<String> get()=_product

    fun productvalue (new:String){

        _product.value = new
    }
}