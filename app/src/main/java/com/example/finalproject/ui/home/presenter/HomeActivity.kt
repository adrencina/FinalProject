package com.example.finalproject.ui.home.presenter

import com.example.finalproject.ui.home.adapter.ProductsAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val productTypesAdapter = ProductTypesAdapter(emptyList(), onClickListener = {productType -> onItemSelected(productType)  })
    private val productsAdapter = ProductsAdapter(emptyList())
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = HomeRepository(this)
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        navigateToEmailSupport()
        setupRecyclerViews()
        observeViewModel()
        navigateToEmailSupport()
        setIconFavorite()
        navigateToFragment()

        homeViewModel.fetchCategories()
        homeViewModel.fetchProducts()
        homeViewModel.fetchFeaturedProduct()

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val lastVisitedProductId = sharedPreferences.getInt("last_visited_product_id", 0)
        if (lastVisitedProductId != 0) {
            homeViewModel.fetchLastVisitedProduct(lastVisitedProductId)
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
        homeViewModel.productTypes.observe(this, Observer { productTypes ->
            productTypesAdapter.updateData(productTypes)
        })

        homeViewModel.products.observe(this, Observer { products ->
            if (products.isNotEmpty()) {
                productsAdapter.updateData(products)
                Log.d("HomeActivity", "Productos mostrados: ${products.size}")
            } else {
                Log.d("HomeActivity", "No se encontraron productos")
            }
        })

        homeViewModel.dailyOffer.observe(this, Observer { dailyOffer ->
            dailyOffer?.let { product ->
                Log.d("HomeActivity", "Recibido producto diario: $product")
                updateFeaturedProduct(product)
            }
        })

//        homeViewModel.lastVisitedProduct.observe(this, Observer { lastVisitedProduct ->
//            lastVisitedProduct?.let { product ->
//                updateFeaturedProduct(product)
//            }
//        })

        homeViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            Log.e("HomeActivity", "Error: $errorMessage")
        })

        //todo observador de consumo a favorite terminar cuando consumo este ok
//        homeViewModel.homeState.observe(this) { favorite ->
//            when (favorite) {
//                is HomeState.Success -> {
//                    binding.iconHeart1.isEnabled = true
//                }
//                is HomeState.Error -> {
//                    binding.iconHeart1.isEnabled = true
//                }
//                is HomeState.Loading -> {}
//            }
//        }
    }

    private fun updateFeaturedProduct(product: DailyOfferResponse) {
        Log.d("HomeActivity", "Actualizando producto destacado: $product")

        binding.tvHomeNameProduct.text = product.name ?: "Producto no disponible"
        binding.tvHomeDescriptionProduct.text = product.description ?: "Sin descripción"
        binding.tvHomePriceProduct.text = product.price?.toString() ?: "Sin precio"

        Log.d("HomeActivity", "Configurando visibilidad de titleDailyOffer a VISIBLE")
        binding.titleDailyOffer.visible(true)

        val imageUrl = product.images?.firstOrNull()?.link ?: ""
        if (imageUrl.isNotEmpty()) {
            Log.d("HomeActivity", "Cargando imagen desde URL: $imageUrl")
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.imgerror)
                .error(R.drawable.imgerror)
                .into(binding.ivHomeProduct)
        } else {
            Log.d("HomeActivity", "URL de imagen vacía, mostrando imagen por defecto")
            binding.ivHomeProduct.setImageResource(R.drawable.imgerror)
        }
    }

    // Navega a soporte por email
    private fun navigateToEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", "rla.support@gmail.com", null)
                )
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        }
    }

    private fun setIconFavorite() {
        var favorite = false
        binding.iconHeart1.setOnClickListener {
            if (favorite) {
                binding.iconHeart1.setImageResource(R.drawable.ic_heart_1)
            } else {
                binding.iconHeart1.setImageResource(R.drawable.ic_heart_2)
            }
            favorite = !favorite
        }
    }


    private fun onItemSelected(productType: ProductType){
        if (productType.idProductType.toString().isNotEmpty()) {
            homeViewModel.products.observe(this, Observer { products ->
                if (products.isNotEmpty()) {
                    productsAdapter.updateData(products)
                    productsAdapter.filtered(productType)
                    Log.d("HomeActivity", "Productos mostrados: ${products.size}")
                } else {
                    Log.d("HomeActivity", "No se encontraron productos")
                }
            })
        }
    }
    private fun navigateToFragment(){
        binding.cvImageProduct.setOnClickListener {
            val intent = Intent(this, LeftBarActivity::class.java)
            startActivity(intent)
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


