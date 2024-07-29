package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.leftbar.viewModel.sharedViewModel

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private val sharedViewModel: sharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receivedProductPrice = intent.getIntExtra("productPrice", -1)
        sharedViewModel.productPricevalue(receivedProductPrice.toString())

        val receivedProductId = intent.getIntExtra("idProduct", -1)
        sharedViewModel.productIdvalue(receivedProductId)

        val receivedProductName = intent.getStringExtra("productName")
        sharedViewModel.productName(receivedProductName.toString())

        sharedViewModel.productPrice.observe(this,{
            binding.tvPrice.text = it.toString()
        })

        sharedViewModel.fragmentTittle.observe(this,{
            binding.fragmentTittle.text = it
        })
    }
}