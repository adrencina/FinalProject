package com.example.finalproject.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.databinding.ItemRvHomeTextProductBinding

class ProductTypeViewHolder(private val binding: ItemRvHomeTextProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(productType: ProductType) {
        binding.tvProductTypeName.text = productType.description
    }
}