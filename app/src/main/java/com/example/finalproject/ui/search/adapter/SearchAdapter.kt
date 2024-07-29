package com.example.finalproject.ui.search.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.example.finalproject.databinding.ItemRvHomeSearchListBinding
import com.example.finalproject.ui.home.adapter.ProductsAdapter
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
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

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

        fun bind(product: Product) {
            binding.tvSeachTitle.text = product.name ?: "No Data"
            binding.tvSearchSubtitle.text = product.description ?: "No description"

            val imageUrl = product.image ?: ""
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

            binding.tvSearchPrice.text = product.price?.toString() ?: "No Data"

            if(product.isFavorite==true){
                binding.ivSearchFullHeart.visibility = View.VISIBLE
                binding.ivSearchEmptyHeart.visibility = View.GONE
            }


        }
    }
}



