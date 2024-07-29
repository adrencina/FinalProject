package com.example.finalproject.ui.leftbar.fragments.comment.state

import com.example.finalproject.data.dto.model.Comment
import com.example.finalproject.data.dto.response.Product

sealed class CommentsState {
    data object Loading : CommentsState()
    data class Success(val comments: List<Comment>) : CommentsState()
//    data class SuccessByProduct (val product: Product) : CommentsState()
    data class Error(val message: String) : CommentsState()
}