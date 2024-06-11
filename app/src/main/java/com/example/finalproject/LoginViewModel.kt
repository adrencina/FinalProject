package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _data = MutableLiveData<LoginState>()
    val data: LiveData<LoginState> = _data

}