package com.example.finalproject.ui.leftbar.fragments.comments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.comments.state.CommentsState
import kotlinx.coroutines.launch

class CommentsViewModel(private val repository: LeftbarRepository) : ViewModel() {

    private val _state = MutableLiveData<CommentsState>()
    val state: LiveData<CommentsState> get() = _state

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
}
