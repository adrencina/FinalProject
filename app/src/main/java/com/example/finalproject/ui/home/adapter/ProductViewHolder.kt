package com.example.finalproject.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(private val binding: ItemRvHomeProductsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.nameRecyclerProduct.text = product.name ?: "No name"
        binding.tvRecyclerPrice.text = product.price?.toString() ?: "No price"
        Picasso.get().load(product.image).into(binding.ivRecyclerProduct)
    }
}