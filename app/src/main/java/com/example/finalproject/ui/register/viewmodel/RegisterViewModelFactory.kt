package com.example.finalproject.ui.register.viewmodel

import RegisterViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.RegisterRepository

class RegisterViewModelFactory(private val registerRepository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(registerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}