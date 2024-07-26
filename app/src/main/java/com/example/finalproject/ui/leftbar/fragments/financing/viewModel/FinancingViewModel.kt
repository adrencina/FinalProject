package com.example.finalproject.ui.leftbar.fragments.financing.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.dto.model.PaymentMethod
import com.example.finalproject.data.repository.PaymentRepository
import kotlinx.coroutines.launch

class FinancingViewModel (private val repository: PaymentRepository) : ViewModel() {

    // LD lista de metodos de pagos
    private val _paymentMethods = MutableLiveData< List<PaymentMethod>>()
    val paymentMethods: LiveData< List<PaymentMethod>> get() = _paymentMethods

    // LD manejar errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun fetchPaymentMethods() {
        viewModelScope.launch {
            try {
                val response = repository.getPaymentMethods()
                if (response.isSuccessful) {
                    response.body()?.let { paymentMethods ->
                        Log.i("DATA1",paymentMethods.paymentMethods.toString())
                        _paymentMethods.postValue(paymentMethods.paymentMethods)
                    } ?: run {
                        _error.postValue("Respuesta vacía")
                    }
                } else {
                    Log.e("HomeView", "Error: ${response.errorBody()?.string()}")
                    _error.postValue("${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error de red: ${e.message}")
                _error.postValue("Error de red: ${e.message}")
            }
        }
    }

}

