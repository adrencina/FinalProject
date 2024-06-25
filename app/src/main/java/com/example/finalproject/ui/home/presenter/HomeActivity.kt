package com.example.finalproject.ui.home.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.service.dto.HomeState
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()

        observeViewModel()

        navigateToEmailSupport()

        homeViewModel.fetchCategories()
        homeViewModel.fetchOnSaleProducts()
    }

    private fun setupRecyclerViews() {
        binding.rvHomeNameItems.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeViewModel() {
        // Observa los cambios en las categorías
        homeViewModel.categories.observe(this, Observer { categories ->
            // Actualiza el RecyclerView de categorías
            // categoriesAdapter.submitList(categories)
            // categoriesAdapter.notifyDataSetChanged()
        })

        // Observa los cambios en los productos
        homeViewModel.products.observe(this, Observer { products ->
            // Actualiza el RecyclerView de productos
            // productsAdapter.submitList(products)
            // productsAdapter.notifyDataSetChanged()
        })

        // Observa los cambios en los productos en oferta
        homeViewModel.onSaleProducts.observe(this, Observer { products ->
            // Actualiza el RecyclerView de productos en oferta
            // onSaleProductsAdapter.submitList(products)
            // onSaleProductsAdapter.notifyDataSetChanged()
        })

        // Observa los errores y muestra un Toast
        homeViewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        // Observa los estados de la API
        homeViewModel.homeState.observe(this, Observer { state ->
            when (state) {
                is HomeState.Loading -> {
                    // Mostrar el estado de carga en la UI
                    // showLoading()
                }
                is HomeState.Success -> {
                    // Ocultar el estado de carga y mostrar el mensaje de éxito
                    // hideLoading()
                    Toast.makeText(this, state.info, Toast.LENGTH_SHORT).show()
                }
                is HomeState.Error -> {
                    // Ocultar el estado de carga y mostrar el mensaje de error
                    // hideLoading()
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun navigateToEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rla.support@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        }
    }
}