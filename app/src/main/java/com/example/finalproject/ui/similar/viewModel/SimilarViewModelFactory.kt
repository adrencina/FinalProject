package com.example.finalproject.ui.similar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.SearchRepository
import com.example.finalproject.data.repository.SimilarRepository
import com.example.finalproject.ui.similar.viewModel.SimilarViewModel

class SimilarViewModelFactory (private val repository: SimilarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimilarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SimilarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}