package com.example.finalproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.databinding.ItemRvHomeTextProductBinding

class ProductTypesAdapter : RecyclerView.Adapter<ProductTypesAdapter.ProductTypeViewHolder>() {

    private val productTypeList = mutableListOf<ProductType>()

    fun submitList(productTypes: List<ProductType>) {
        productTypeList.clear()
        productTypeList.addAll(productTypes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypeViewHolder {
        val binding = ItemRvHomeTextProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductTypeViewHolder, position: Int) {
        holder.bind(productTypeList[position])
    }

    override fun getItemCount(): Int = productTypeList.size

    class ProductTypeViewHolder(private val binding: ItemRvHomeTextProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productType: ProductType) {
            binding.tvProductTypeName.text = productType.description
        }
    }
}




