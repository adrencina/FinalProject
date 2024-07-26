package com.example.finalproject.ui.leftbar.fragments.comments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.LeftbarRepository

class CommentsViewModelFactory(private val repository: LeftbarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}