package com.example.finalproject.ui.home.presenter

import com.example.finalproject.ui.home.adapter.ProductsAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.service.HomeApiServiceImpl
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: HomeRepository
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val productsAdapter = ProductsAdapter(emptyList())
    private val productTypesAdapter = ProductTypesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // servicio API
        val apiService = HomeApiServiceImpl()

        // repositorio
        repository = HomeRepository(apiService)

        // instancia de HomeViewModel usando HomeViewModelFactory
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        setupRecyclerViews()
        observeViewModel()
        navigateToEmailSupport()

        homeViewModel.fetchCategories()
        homeViewModel.fetchProducts()
        homeViewModel.fetchFeaturedProduct()

        binding.cvImageProduct.setOnClickListener {
            val intent = Intent(this, LeftBarActivity::class.java)
            intent.putExtra("initialFragment", R.id.imagesFragment)
            startActivity(intent)
        }
    }

    // Config RV
    private fun setupRecyclerViews() {
        binding.rvHomeNameItems.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = productTypesAdapter
        }
        // Config RV para los productos
        binding.rvHomeProducts.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = productsAdapter
        }
    }

    // Observamos el VM
    private fun observeViewModel() {
        homeViewModel.productTypes.observe(this, Observer {
            productTypesAdapter.submitList(it)
        })

        homeViewModel.products.observe(this, Observer { products ->
            productsAdapter.updateData(products)
        })

        homeViewModel.dailyOffer.observe(this, Observer { product ->
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

    // Actualiza CV de Home
    private fun updateFeaturedProductCard(product: Product) {
        binding.tvHomeNameProduct.text = product.name ?: "Sin nombre"
        binding.tvHomePriceProduct.text = "${product.currency} ${product.price}"
        binding.tvHomeDescriptionProduct.text = product.description
        Picasso.get().load(product.images?.link).into(binding.ivHomeProduct)
    }

    // Navega a soporte por email
    private fun navigateToEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rla.support@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        }
    }
}


// Éste codigo de abajo es para el SearchView que se trabajará en proximos días...

//initSearchRecyclerView()
//initSearchView()
//searchViewObserver()

//private lateinit var searchList: MutableList<Product>
//private lateinit var searchAdapter: SearchAdapter
//private var searchLLmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//
//    // Inicializa RecyclerView de búsqueda
//    private fun initSearchRecyclerView() {
//        searchAdapter = SearchAdapter(
//            productLst = searchList,
//            onClickListener = { product -> onItemSelected(product) }
//        )
//        binding.rvHomeSearch.layoutManager = searchLLmanager
//        binding.rvHomeSearch.adapter = searchAdapter
//    }
//
//    // Acción cuando se selecciona un ítem en la búsqueda
//    private fun onItemSelected(product: Product) {
//        Toast.makeText(this, "Seleccionado: ${product.name}", Toast.LENGTH_SHORT).show()
//    }
//
//    // Inicializar SearchView
//    private fun initSearchView() {
//        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (!query.isNullOrEmpty()) {
//                    homeViewModel.searchViewController(true)
//                    val filtered = searchList.filter { product ->
//                        product.name?.contains(query, ignoreCase = true) ?: false
//                    }
//                    searchAdapter.update(filtered)
//                } else {
//                    homeViewModel.searchViewController(false)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (!newText.isNullOrEmpty()) {
//                    homeViewModel.searchViewController(true)
//                    val filtered = searchList.filter { product ->
//                        product.name?.contains(newText, ignoreCase = true) ?: false
//                    }
//                    searchAdapter.update(filtered)
//                } else {
//                    homeViewModel.searchViewController(false)
//                }
//                return true
//            }
//        })
//    }
//
//    // Observar cambios en el resultado de búsqueda
//    private fun searchViewObserver() {
//        homeViewModel.searchResult.observe(this) { result ->
//            searchVisibility(result)
//        }
//    }
//
//    // Controlar visibilidad del resultado de búsqueda
//    private fun searchVisibility(result: Boolean) {
//        binding.cvImageProduct.isVisible = !result
//        binding.rvHomeProducts.isVisible = !result
//        binding.rvHomeNameItems.isVisible = !result
//        binding.ivLine.isVisible = !result
//        binding.rvHomeSearch.isVisible = result
//    }
//}