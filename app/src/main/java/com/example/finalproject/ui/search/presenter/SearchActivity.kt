package com.example.finalproject.ui.search.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.finalproject.R
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.SearchRepository
import com.example.finalproject.data.service.dto.Utils.ID_PRODUCT
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.databinding.ActivitySearchBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity
import com.example.finalproject.ui.search.adapter.SearchAdapter
import com.example.finalproject.ui.search.viewModel.SearchViewModel
import com.example.finalproject.ui.search.viewModel.SearchViewModelFactory
import com.squareup.picasso.Picasso

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private  var searchList: MutableList<Product> = mutableListOf()
    private lateinit var searchAdapter: SearchAdapter
    private var searchLLmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    private var query = false
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = SearchRepository(this)
        searchViewModel =
            ViewModelProvider(this, SearchViewModelFactory(repository))[SearchViewModel::class.java]

        initSearchRecyclerView()
        observeViewModel()
        initSearchView()
        buttonHeard()

        searchViewModel.fetchProductsSearch()
    }

// Inicializa RecyclerView de búsqueda
   private fun initSearchRecyclerView(){
       searchAdapter = SearchAdapter(
           products = searchList,
            onClickListener = { product -> onItemSelected(product) }
        )
        binding.srReacyclerView.layoutManager = searchLLmanager
        binding.srReacyclerView.adapter = searchAdapter
   }


// Acción cuando se selecciona un ítem en la búsqueda
    private fun onItemSelected(product: Product) {
        Toast.makeText(this, "Seleccionado: ${product.name}", Toast.LENGTH_SHORT).show()
    }

    // Inicializar SearchView
    private fun initSearchView() {
        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    Log.i("DATA-Search-onQueryTextSubmit",query)
                     searchViewModel.fetchProductsSearch(productName = query)
                    if (query=="") searchViewModel.fetchProductsSearch()
                     binding.iconHeart4.visibility = View.GONE
                     binding.iconHeart3.visibility = View.VISIBLE
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    Log.i("DATA-Search-onQuery",newText)
                }
                return true
            }
        })
    }

    private fun buttonHeard(){
        binding.iconHeart3.setOnClickListener{
            if(!query){
                Log.i("DATA-Search-Heart","click")
                searchViewModel.fetchProductsSearch(onlyFavorite=true)
                binding.iconHeart3.visibility = View.GONE
                binding.iconHeart4.visibility = View.VISIBLE
                query=true
            }
        }
        binding.iconHeart4.setOnClickListener {
            if(!query){
                Log.i("DATA-Search-Heart","click")
                searchViewModel.fetchProductsSearch(onlyFavorite=false)
                binding.iconHeart4.visibility = View.GONE
                binding.iconHeart3.visibility = View.VISIBLE
                query=true
            }
        }
    }

    // Observamos el VM
    private fun observeViewModel() {

        searchViewModel.products.observe(this, Observer { products ->
            if (products.isNotEmpty()) {
                Log.i("DATA-Product",products.size.toString())
                if(query) query=false
                binding.srReacyclerView.visibility = View.VISIBLE
                binding.notSearchIcon.visibility = View.GONE
                binding.notSearchText.visibility = View.GONE

                searchAdapter.updateFilter(products)
                Log.d("SearchActivity", "Productos mostrados: ${products.size}")
            } else {
                Log.d("SearchActivity", "No se encontraron productos")
                binding.srReacyclerView.visibility = View.GONE
                binding.notSearchIcon.visibility = View.VISIBLE
                binding.notSearchText.visibility = View.VISIBLE
            }

        })


//        homeViewModel.lastVisitedProduct.observe(this, Observer { lastVisitedProduct ->
//            lastVisitedProduct?.let { product ->
//                updateFeaturedProduct(product)
//            }
//        })

        searchViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            Log.e("SearchActivity", "Error: $errorMessage")
        })


    }

}






