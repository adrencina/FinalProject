package com.example.finalproject.ui.leftbar.fragments.comments.state

import com.example.finalproject.data.dto.model.Comment

sealed class CommentsState {
    data object Loading : CommentsState()
    data class Success(val comments: List<Comment>) : CommentsState()
    data class Error(val message: String) : CommentsState()
}