package com.example.finalproject.ui.commentsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.dto.response.Product

class CommentViewModel : ViewModel() {
    private val _commentt = MutableLiveData<List<Product>>()
    val comment: LiveData<List<Product>> get() = _commentt





}