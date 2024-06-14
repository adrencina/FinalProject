package com.example.finalproject.ui.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Data.Service.dto.RegisterState

class RegisterViewModel : ViewModel() {
    private val _data = MutableLiveData<RegisterState>()
    val data: LiveData<RegisterState> = _data
}