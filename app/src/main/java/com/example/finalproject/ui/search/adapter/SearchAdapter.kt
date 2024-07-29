package com.example.finalproject.ui.search.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeSearchListBinding
import com.squareup.picasso.Picasso

class SearchAdapter(
    private var products: List<Product>,
    private val onClickListener: (Product) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRvHomeSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product,onClickListener)
    }

    override fun getItemCount(): Int = products.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newProductList: List<Product>) {
        products = newProductList
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateFilter(product: List<Product>){
        products = product
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemRvHomeSearchListBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(product: Product,onClickListener: (Product) -> Unit) {
            val price = product.price
            val currency = product.currency
            val productPrice = "${currency+price} "
            binding.tvSeachTitle.text = product.name ?: "No Data"
            binding.tvSearchSubtitle.text = product.description ?: "No description"

            val imageUrl = product.images?.firstOrNull()?.link ?: ""
            if (imageUrl.isNotEmpty()) {
                Log.d("ProductsAdapter", "Cargando imagen desde URL: $imageUrl")

                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.imgerror) // Imagen placeholder mientras se carga
                    .error(R.drawable.imgerror) // Imagen por defecto en caso de error
                    .into(binding.ivSearchPhoto)
            } else {
                Log.d("ProductsAdapter", "URL de imagen vacía, mostrando imagen por defecto")
                binding.ivSearchPhoto.setImageResource(R.drawable.imgerror) // Imagen Placeholder
            }

            binding.tvSearchPrice.text = productPrice

            if(product.isFavorite==true){
                binding.ivSearchFullHeart.visibility = View.VISIBLE
                binding.ivSearchEmptyHeart.visibility = View.GONE
            }
            binding.searchButton.setOnClickListener {
                onClickListener(product)
            }


        }
    }
}



