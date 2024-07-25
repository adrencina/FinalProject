package com.example.finalproject.ui.leftbar.fragments.description.viewModel

import LeftbarViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.home.viewModel.HomeViewModel

class DescriptionViewModelFactory(private val repository: LeftbarRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DescriptionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DescriptionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}