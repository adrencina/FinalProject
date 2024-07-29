package com.example.finalproject.ui.similar.presenter

import com.example.finalproject.ui.home.adapter.ProductsAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.ProductInfo.P
import com.example.finalproject.data.repository.SearchRepository
import com.example.finalproject.data.repository.SimilarRepository
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.databinding.ActivitySearchBinding
import com.example.finalproject.databinding.ActivitySimilarBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity
import com.example.finalproject.ui.search.adapter.SearchAdapter
import com.example.finalproject.ui.search.presenter.SearchActivity
import com.example.finalproject.ui.search.viewModel.SearchViewModel
import com.example.finalproject.ui.search.viewModel.SearchViewModelFactory
import com.example.finalproject.ui.similar.viewModel.SimilarViewModel
import com.example.finalproject.ui.similar.viewModel.SimilarViewModelFactory
import com.squareup.picasso.Picasso

class SimilarActivity : AppCompatActivity() {

    private lateinit var similarViewModel: SimilarViewModel
    private  var similarList: MutableList<Product> = mutableListOf()
    private lateinit var similarAdapter: SearchAdapter
    private var similarManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    private lateinit var binding: ActivitySimilarBinding
    private var id = 0
    private var productPrice = 0
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivitySimilarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedProductId = intent.getIntExtra("idProduct", -1)
        Log.i("Similar", receivedProductId.toString())
        id = receivedProductId
        //sharedViewModel.productIdvalue(receivedProductId)

        val receivedProductPrice = intent.getIntExtra("productPrice", -1)
        Log.i("Similar", receivedProductPrice.toString())
        productPrice = receivedProductPrice
        //sharedViewModel.productPricevalue(receivedProductPrice.toString())

        val receivedProductName = intent.getStringExtra("productName")
        Log.i("Similar", receivedProductName.toString())
        name = receivedProductName ?: ""
        //sharedViewModel.productName(receivedProductName.toString())

        val repository = SimilarRepository(this)
        similarViewModel =
            ViewModelProvider(this, SimilarViewModelFactory(repository))[SimilarViewModel::class.java]

        navigateToFragment()
        initSearchRecyclerView()
        observeViewModel()
        similarViewModel.fetchProductsSimilar((P?.idProduct ?: 1))
    }

    // Inicializa RecyclerView de búsqueda
    private fun initSearchRecyclerView(){
        similarAdapter = SearchAdapter(
            products = similarList,
            onClickListener = { product -> onItemSelected(product) }
        )
        binding.rvSimilar.layoutManager = similarManager
        binding.rvSimilar.adapter = similarAdapter
    }

    // Acción cuando se selecciona un ítem en la búsqueda
    private fun onItemSelected(product: Product) {
        Toast.makeText(this, "Seleccionado: ${product.name}", Toast.LENGTH_SHORT).show()
    }

    // Configuración de RecyclerViews
    private fun setupRecyclerViews() {

        binding.rvSimilar.apply {
            layoutManager =
                LinearLayoutManager(this@SimilarActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarAdapter
        }


    }

    // Observamos el VM
    private fun observeViewModel() {

        similarViewModel.products.observe(this, Observer { products ->
            if (products.isNotEmpty()) {
                Log.i("DATA-Product",products.size.toString())
                similarAdapter.updateFilter(products)
                Log.d("SearchActivity", "Productos mostrados: ${products.size}")
            } else {
                Log.d("SearchActivity", "No se encontraron productos")
            }

        })

        similarViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            Log.e("SearchActivity", "Error: $errorMessage")
        })

    }
    // Navega a LeftBarActivity
    private fun navigateToFragment() {
        binding.BtnBack.setOnClickListener {
            val intent = Intent(this, LeftBarActivity::class.java)
            intent.putExtra("idProduct", id)
            intent.putExtra("productPrice", productPrice)
            intent.putExtra("productName",name)
            startActivity(intent)
        }
    }

}



