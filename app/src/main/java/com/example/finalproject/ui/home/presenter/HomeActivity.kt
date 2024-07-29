package com.example.finalproject.ui.home.presenter

import com.example.finalproject.ui.home.adapter.ProductsAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.data.dto.response.DailyOfferResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.ProductInfo.P
import com.example.finalproject.data.repository.TokenManager
import com.example.finalproject.databinding.ActivityHomeBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.adapter.ProductTypesAdapter
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity
import com.example.finalproject.ui.search.presenter.SearchActivity
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val productTypesAdapter = ProductTypesAdapter(
        emptyList(),
        onClickListener = { productType -> onItemSelected(productType) })
    private val productsAdapter = ProductsAdapter(emptyList(), onProductClick = {product -> onSelectedItem(product)  })
    private lateinit var homeViewModel: HomeViewModel
    private var id = 0
    private var productPrice = 0
    private var name = "name"
    private var currencyType = "currency"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = HomeRepository(this)
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        navigateToFragment()
        setupRecyclerViews()
        observeViewModel()
        navigateToEmailSupport()
        setIconFavorite()
        initSearchView()

        homeViewModel.fetchCategories()
        homeViewModel.fetchProducts()
        homeViewModel.fetchFeaturedProduct()

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val lastVisitedProductId = sharedPreferences.getInt("last_visited_product_id", 0)
        if (lastVisitedProductId != 0) {
            homeViewModel.fetchLastVisitedProduct(lastVisitedProductId)
        }
    }

    // Configuración de RecyclerViews
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

    // Observamos el ViewModel
    private fun observeViewModel() {
        homeViewModel.productTypes.observe(this) { productTypes ->
            productTypesAdapter.updateData(productTypes)
        }

        homeViewModel.products.observe(this) { products ->
            if (products.isNotEmpty()) {
                productsAdapter.updateData(products)
            } else {
                Log.d("HomeActivity", "No se encontraron productos")
            }
        }

        homeViewModel.dailyOffer.observe(this) { dailyOffer ->
            dailyOffer?.let { product ->
                Log.d("HomeActivity", "Recibido producto diario: $product")
                updateFeaturedProduct(product)
            }
        }

        homeViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            Log.e("HomeActivity", "Error: $errorMessage")
        }
    }

    // Actualiza el producto destacado
    private fun updateFeaturedProduct(product: DailyOfferResponse) {
        P = Product(
            idProduct= product.idProduct,
            name= product.name,
             productType= product.productType,
             currency= product.currency,
             price= product.price,
             images= product.images,
             description= product.description,
             isFavorite= product.isFavorite
        )
        val price = product.price
        val currency = product.currency
        val prodPrice = "${currency+price} "

        binding.tvHomeNameProduct.text = product.name ?: "Producto no disponible"
        binding.tvHomeDescriptionProduct.text = product.description ?: "Sin descripción"
        binding.tvHomePriceProduct.text = prodPrice

        binding.titleDailyOffer.visible(true)

        id = product.idProduct ?: 0
        productPrice = product.price?.toInt() ?: 0
        name = product.name.toString()
        currencyType = product.currency.toString()

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

    // Configura el ícono de favorito
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

    // Maneja la selección de un tipo de producto
//    private fun onItemSelected(productType: ProductType) {
//        homeViewModel.products.observe(this) { products ->
//            val filteredProducts = products.filter { product ->
//                product.productType?.idProductType == productType.idProductType
//            }
//            productsAdapter.updateData(filteredProducts)
//        }
//    }

    private fun onItemSelected(productType: ProductType){

        if (productType.idProductType.toString().isNotEmpty()) {
            homeViewModel.products.observe(this) { products ->
                if (products.isNotEmpty()) {
                    productsAdapter.updateData(products)
                    productsAdapter.filtered(productType)
                    Log.d("HomeActivity", "Productos mostrados: ${products.size}")
                } else {
                    Log.d("HomeActivity", "No se encontraron productos")
                }
            }
        }
    }

    private fun onSelectedItem(product: Product){
        recyclerNavigateToFragment(product)
    }
    private fun recyclerNavigateToFragment(product: Product){
        val intent = Intent(this,LeftBarActivity::class.java)
        intent.putExtra("idProduct",product.idProduct)
        intent.putExtra("productPrice",product.price?.toInt())
        intent.putExtra("productName",product.name)
        P = product
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        TokenManager.clearData(this)
        }

    // Navega a LeftBarActivity
//    private fun navigateToLeftBarActivity(idProduct: Int, productPrice: Double) {
//        val intent = Intent(this, LeftBarActivity::class.java).apply {
//            putExtra("idProduct", idProduct)
//            putExtra("productPrice", productPrice)
//        }
//        startActivity(intent)
//    }

    // Navega a LeftBarActivity
    private fun navigateToFragment() {
        binding.cvImageProduct.setOnClickListener {
            val intent = Intent(this, LeftBarActivity::class.java)
            intent.putExtra("idProduct", id)
            intent.putExtra("productPrice", productPrice)
            intent.putExtra("productName",name)
            startActivity(intent)
        }
    }


    // Inicializa la barra de búsqueda
    private fun initSearchView() {
        binding.svHome.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    goToSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    goToSearch(newText)
                }
                return true
            }
        })
    }

    // Navega a la actividad de búsqueda
    private fun goToSearch(query: String) {
        val intent = Intent(this, SearchActivity::class.java)
        intent.putExtra("search", query)
        startActivity(intent)
    }
}



