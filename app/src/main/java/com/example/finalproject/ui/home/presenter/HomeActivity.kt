package com.example.finalproject.ui.home.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.Utils.visible
import com.example.finalproject.data.dto.model.Product
import com.example.finalproject.data.service.dto.HomeState
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.recycler.adapter.rvSearchs.SearchAdapter
import com.example.finalproject.ui.home.recycler.productProvider
import com.example.finalproject.ui.home.viewModel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private var searchList: MutableList<Product> = productProvider.productLst.toMutableList()
    private lateinit var searchAdapter: SearchAdapter
    private var searchLLmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViews()
        observeViewModel()
        navigateToEmailSupport()
        initSearchRecyclerView()

        initSearchView()
        searchViewObserver()


//        homeViewModel.fetchCategories()
//        homeViewModel.fetchOnSaleProducts()
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

        // Observamos los estados de la API

        homeViewModel.homeState.observe(this, Observer { state ->
            when (state) {
                is HomeState.Loading -> {
//                    Mostrar el estado de carga en la UI
//                    showLoading()
                }

                is HomeState.Success -> {
//                    Ocultar el estado de carga y mostrar el mensaje de éxito
//                    hideLoading()
                    Toast.makeText(this, state.info, Toast.LENGTH_SHORT).show()
                }

                is HomeState.Error -> {
//                    Ocultar el estado de carga y mostrar el mensaje de error
//                    hideLoading()
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun navigateToEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rla.support@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "enviar email..."))
        }

    }

    private fun initSearchRecyclerView() {
        searchAdapter = SearchAdapter(
            productLst = searchList,
            onClickListener = { position -> onItemSelected(position) }
        )
        binding.rvHomeSearch.layoutManager = searchLLmanager
        binding.rvHomeSearch.adapter = searchAdapter
    }

    private fun onItemSelected(position: Product) {
        Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
    }


    private fun initSearchView() {
        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {

                    homeViewModel.searchViewController(true)
                    val filtered =
                        searchList.filter { product -> product.name.contains(query.toString()) }
                    searchAdapter.update(filtered)
                } else {
                    homeViewModel.searchViewController(false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    homeViewModel.searchViewController(true)
                    val filtered =
                        searchList.filter { product -> product.name.contains(newText.toString()) }
                    searchAdapter.update(filtered)
                } else {
                    homeViewModel.searchViewController(false)
                }
                return true
            }
        })
    }

    private fun searchViewObserver() {
        homeViewModel.searchResult.observe(this) { result ->
            when (result) {
                true -> {
                    searchVisibity(result)
                }
                false -> {
                    searchVisibity(result)
                }
            }
        }
    }

    private fun searchVisibity(result:Boolean) {
        binding.cvImageProduct.visible(!result)
        binding.rvHomeProducts.visible(!result)
        binding.rvHomeNameItems.visible(!result)
        binding.ivLine.visible(!result)
        binding.rvHomeSearch.visible(result)

    }


}