package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.leftbar.viewModel.SharedViewModel

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receivedProductPrice = intent.getIntExtra("productPrice", -1)
        sharedViewModel.productPricevalue(receivedProductPrice.toString())


        val receivedProductId = intent.getIntExtra("idProduct", -1)
        sharedViewModel.productIdvalue(receivedProductId)
    }
}