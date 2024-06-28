package com.example.finalproject.ui.home.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductsAdapter
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private val productsAdapter = ProductsAdapter()
    private val productTypesAdapter = ProductTypesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()
        observeViewModel()
        navigateToEmailSupport()

        homeViewModel.fetchCategories()
        homeViewModel.fetchProducts()
        homeViewModel.fetchFeaturedProduct()
    }

    private fun setupRecyclerViews() {
        binding.rvHomeNameItems.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = productTypesAdapter
        }

        binding.rvHomeProducts.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = productsAdapter
        }
    }

    private fun observeViewModel() {
        homeViewModel.categories.observe(this, Observer { categories ->
            productTypesAdapter.submitList(categories)
        })

        homeViewModel.products.observe(this, Observer { products ->
            productsAdapter.submitList(products)
        })

        homeViewModel.featuredProduct.observe(this, Observer { product ->
            product?.let {
                updateFeaturedProductCard(it)
            }
        })

        homeViewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateFeaturedProductCard(product: Product) {
        binding.tvHomeNameProduct.text = product.name
        binding.tvHomePriceProduct.text = "${product.currency} ${product.price}"
        binding.tvHomeDescriptionProduct.text = product.description
        Picasso.get()
            .load(product.image)
            .into(binding.ivHomeProduct)
    }

    private fun navigateToEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rla.support@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        }
    }
}