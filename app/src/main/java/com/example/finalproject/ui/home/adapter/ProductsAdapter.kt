package com.example.finalproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val productList = mutableListOf<Product>()

    fun submitList(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRvHomeProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(private val binding: ItemRvHomeProductsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.nameRecyclerProduct.text = product.name
            binding.tvRecyclerPrice.text = "${product.currency}${product.price}"
            Picasso.get().load(product.image).into(binding.ivRecyclerProduct)
        }
    }
}



