package com.example.finalproject.ui.leftbar.presenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.buy.BuyActivity
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.leftbar.viewModel.SharedFragViewModel

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private val sharedViewModel: SharedFragViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedProductPrice = intent.getIntExtra("productPrice", -1)
        sharedViewModel.productPricevalue(receivedProductPrice.toString())

        val receivedProductId = intent.getIntExtra("idProduct", -1)
        Log.i("asd", receivedProductId.toString())
        sharedViewModel.productIdvalue(receivedProductId)

        val receivedProductName = intent.getStringExtra("productName")
        Log.i("asd",receivedProductName.toString())
        sharedViewModel.productName(receivedProductName.toString())

        sharedViewModel.productPrice.observe(this) {
            binding.tvPrice.text = it.toString()
        }

        sharedViewModel.fragmentTittle.observe(this) {
            binding.fragmentTittle.text = it
        }

        binding.BtnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        }
    }
}