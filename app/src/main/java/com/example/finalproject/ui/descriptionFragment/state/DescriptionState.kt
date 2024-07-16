package com.example.finalproject.ui.descriptionFragment.state




sealed class DescriptionState {
    data class Success(val data:String):DescriptionState() // cambiar valor data a su respectivo response
    data class Error(val message:String):DescriptionState()

    data object Loading : DescriptionState()
}