package com.example.finalproject.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.service.HomeApiServiceImp
import com.example.finalproject.data.service.dto.HomeData
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeApiService = HomeApiServiceImp()

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> get() = _homeData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchHomeData() {
        viewModelScope.launch {
            try {
                val response = homeApiService.getHomeData()
                if (response.isSuccessful) {
                    _homeData.postValue(response.body())
                } else {
                    _error.postValue(response.message())
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}