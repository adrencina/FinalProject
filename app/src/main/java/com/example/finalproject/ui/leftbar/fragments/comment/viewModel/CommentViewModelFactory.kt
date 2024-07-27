package com.example.finalproject.ui.leftbar.fragments.comment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.data.service.CommentsApiServiceImp
import com.example.finalproject.data.service.CommentsRepository
import com.example.finalproject.data.service.LeftbarApiServiceImp

class CommentsViewModelFactory(private val repository: CommentsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}