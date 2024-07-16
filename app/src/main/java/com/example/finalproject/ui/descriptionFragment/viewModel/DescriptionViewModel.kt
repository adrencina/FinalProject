package com.example.finalproject.ui.descriptionFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.ui.descriptionFragment.state.DescriptionState

class DescriptionViewModel:ViewModel() {

    private val _descriptionState = MutableLiveData<DescriptionState>()
    val descriptionState : LiveData<DescriptionState> = _descriptionState
}