package com.example.finalproject.ui.leftbar.viewModel

import LeftbarViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.LeftbarRepository

class LeftbarViewModelFactory(private val repository: LeftbarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeftbarViewModel::class.java)) {
            return LeftbarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}