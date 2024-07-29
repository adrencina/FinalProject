package com.example.finalproject.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private var products: List<Product>,
    private val onProductClick: (Int, Double) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRvHomeProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        Log.d("ProductsAdapter", "Product: ${product.name}, Image URL: ${product.images?.firstOrNull()?.link}")
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProductList: List<Product>) {
        products = newProductList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemRvHomeProductsBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product, onClickListener: (Product) -> Unit) {
            binding.nameRecyclerProduct.text = product.name ?: "No Data"
            binding.tvRecyclerPrice.text = "${product.currency}${product.price}"

            val imageUrl = product.images?.firstOrNull()?.link ?: ""
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.imgerror) // Imagen placeholder mientras se carga
                    .error(R.drawable.imgerror) // Imagen por defecto en caso de error
                    .into(binding.ivRecyclerProduct)
            } else {
                binding.ivRecyclerProduct.setImageResource(R.drawable.imgerror) // Imagen Placeholder
            }

            binding.cvRecyclerProduct.setOnClickListener {
                product.idProduct?.let { id ->
                    product.price?.let { price ->
                        onProductClick(id, price)
                    }
                }
            }
        }
    }
}