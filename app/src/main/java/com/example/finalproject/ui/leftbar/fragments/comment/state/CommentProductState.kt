package com.example.finalproject.ui.leftbar.fragments.comment.state

import com.example.finalproject.data.dto.response.Product

sealed class CommentProductState {
    data object Loading :  CommentProductState()
    data class Success(val product: Product) : CommentProductState()
    data class Error(val message: String) : CommentProductState()
}