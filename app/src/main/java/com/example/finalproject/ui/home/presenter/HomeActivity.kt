package com.example.finalproject.ui.home.presenter

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
        homeViewModel.categories.observe(this, Observer { categories ->

//            categoriesAdapter.submitList(categories)
//            categoriesAdapter.notifyDataSetChanged()

        })

        homeViewModel.products.observe(this, Observer { products ->

//            productsAdapter.submitList(products)
//            productsAdapter.notifyDataSetChanged()

        })

        homeViewModel.onSaleProducts.observe(this, Observer { products ->

//            onSaleProductsAdapter.submitList(products)
//            onSaleProductsAdapter.notifyDataSetChanged()

        })

        homeViewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }
}