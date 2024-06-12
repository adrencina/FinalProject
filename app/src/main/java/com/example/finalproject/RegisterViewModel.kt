package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val _data = MutableLiveData<RegisterState>()
    val data: LiveData<RegisterState> = _data
}