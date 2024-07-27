package com.example.finalproject.ui.leftbar.fragments.comment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.service.CommentsRepository
import com.example.finalproject.ui.leftbar.fragments.comment.state.CommentsState
import com.example.finalproject.ui.leftbar.fragments.comment.state.commProductState

import kotlinx.coroutines.launch

class CommentsViewModel(private val repository: CommentsRepository) : ViewModel() {

    private val _state = MutableLiveData<CommentsState>()
    val state: LiveData<CommentsState> get() = _state

    private val _product = MutableLiveData<commProductState>()
    val product: LiveData<commProductState> get()=_product


    fun fetchCommentsByProductId(idProduct: Int) {
        _state.value = CommentsState.Loading

        viewModelScope.launch {
            try {
                val response = repository.getCommentsByProductId(idProduct)
                if (response.isSuccessful) {
                    response.body()?.let { commentResponse ->
                        _state.value = CommentsState.Success(commentResponse.comments)
                    } ?: run {
                        _state.value = CommentsState.Error("No comments found")
                    }
                } else {
                    _state.value = CommentsState.Error("Error fetching comments")
                }
            } catch (e: Exception) {
                _state.value = CommentsState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun getProductById(idProduct: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(idProduct)
                _product.value = commProductState.Success(product)
            } catch (e: Exception) {
                _product.value = commProductState.Error("Error al cargar el producto:${e.message}")
            }
            }

        }
}