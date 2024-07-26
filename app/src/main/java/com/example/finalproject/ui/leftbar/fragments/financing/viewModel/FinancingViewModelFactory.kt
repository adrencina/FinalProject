package com.example.finalproject.ui.leftbar.fragments.financing.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.data.repository.PaymentRepository

class FinancingViewModelFactory(private val repository: PaymentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinancingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FinancingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}