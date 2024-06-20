package com.example.finalproject.ui.home.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel.homeData.observe(this, Observer { homeData ->
            binding.tvHomeNameProduct.text = homeData.name
            binding.tvHomeDescriptionProduct.text = homeData.description
            binding.tvHomePriceProduct.text = homeData.price.toString()

            // Cargar la imagen en el ImageView... esto depende de la API y el rv

        })

        homeViewModel.error.observe(this, Observer { error ->

            Toast.makeText(this, error, Toast.LENGTH_LONG).show() // Muestra el error en un Toast
        })

        homeViewModel.fetchHomeData()
    }
}