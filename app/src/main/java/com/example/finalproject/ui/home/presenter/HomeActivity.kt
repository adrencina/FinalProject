package com.example.finalproject.ui.home.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
        homeViewModel.fetchProducts()
//        homeViewModel.fetchOnSaleProducts()
        homeViewModel.fetchLastUserProduct()
    }

    private fun setupRecyclerViews() {
        binding.rvHomeNameItems.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeViewModel() {
        homeViewModel.categories.observe(this, Observer { categories ->
            // categoriesAdapter.submitList(categories)
            // categoriesAdapter.notifyDataSetChanged()
        })

        homeViewModel.products.observe(this, Observer { products ->
            // productsAdapter.submitList(products)
            // productsAdapter.notifyDataSetChanged()
        })

        homeViewModel.onSaleProducts.observe(this, Observer { product ->
            // onSaleProductsAdapter.submitList(listOf(product))
            // onSaleProductsAdapter.notifyDataSetChanged()
        })

        homeViewModel.lastUserProduct.observe(this, Observer { product ->
            // lastUserProductAdapter.submitList(listOf(product))
            // lastUserProductAdapter.notifyDataSetChanged()
        })

        homeViewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
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
