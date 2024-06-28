package com.example.finalproject.ui.home.recycler.adapter.rvProducts

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso


class ProductViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemRvHomeProductsBinding.bind(view)

    fun render(productModel: Product, onClickListener: (Product) -> Unit) {
        binding.nameRecyclerProduct.text = productModel.name ?: "No info"
        binding.tvRecyclerPrice.text = productModel.price ?: "No info"

        val imageUrl = productModel.image ?: ""
        if (imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivRecyclerProduct)
        } else {
            binding.ivRecyclerProduct.setImageResource(R.drawable.ic_launcher_background)
        }

        itemView.setOnClickListener {
            onClickListener(productModel)
        }
    }
}