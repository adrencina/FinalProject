package com.example.finalproject.ui.home.recycler.adapter.rvProducts

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finalproject.data.dto.model.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso


class ProductViewHolder(view: View):ViewHolder(view) {

    val binding = ItemRvHomeProductsBinding.bind(view)

    fun render(productModel: Product, onClickListener:(Product) -> Unit){
        binding.nameRecyclerProduct.text = productModel.name
        binding.tvRecyclerPrice.text = productModel.price
        Picasso.get().load(productModel.photo).into(binding.ivRecyclerRoduct)
        itemView.setOnClickListener {
            onClickListener(productModel)
        }
    }
}