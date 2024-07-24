package com.example.finalproject.ui.leftbar.fragments.description.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.repository.DescriptionRepository
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import kotlinx.coroutines.launch

class DescriptionViewModel(private val repository: DescriptionRepository = DescriptionRepository()) : ViewModel() {

    private val _descriptionState = MutableLiveData<DescriptionState>()
    val descriptionState: LiveData<DescriptionState> = _descriptionState

    fun getDescription() {
        viewModelScope.launch {
           val response = repository.getDescription()
            if (response.isSuccessful){
                response.body()?.let {
                    _descriptionState.postValue(DescriptionState.Success(it))
                }?: _descriptionState.postValue(DescriptionState.Error("Error"))
            }else{
                _descriptionState.postValue(DescriptionState.Error("Error"))
            }
        }
    }
}