package com.example.finalproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private var products: List<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRvHomeProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    fun updateData(newProductList: List<Product>) {
        products = newProductList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemRvHomeProductsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.nameRecyclerProduct.text = product.name ?: "No Data"
            binding.tvRecyclerPrice.text = product.price?.toString() ?: "No Data"
            val imageUrl = product.images?.link ?: ""
            if (imageUrl.isNotEmpty()) {
                Picasso.get().load(imageUrl).into(binding.ivRecyclerProduct)
            } else {
                binding.ivRecyclerProduct.setImageResource(R.drawable.ic_launcher_background) // Imagen Placeholder
            }
        }
    }
}




