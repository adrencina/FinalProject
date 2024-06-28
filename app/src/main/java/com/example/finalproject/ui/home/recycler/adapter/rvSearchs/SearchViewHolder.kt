package com.example.finalproject.ui.home.recycler.adapter.rvSearchs

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.ItemRvHomeSearchListBinding
import com.squareup.picasso.Picasso



class SearchViewHolder(view: View): ViewHolder(view) {

    private val binding = ItemRvHomeSearchListBinding.bind(view)

    fun render(productModel: Product, onClickListener: (Product) -> Unit) {
        binding.tvSeachTitle.text = productModel.name ?: "No info"
        binding.tvSearchSubtitle.text = productModel.description ?: "No info"
        binding.tvSearchPrice.text = "${productModel.currency} ${productModel.price}" ?: "No info"

        val imageUrl = productModel.image ?: ""
        if (imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivSearchPhoto)
        } else {
            binding.ivSearchPhoto.setImageResource(R.drawable.ic_launcher_background)
        }

        itemView.setOnClickListener {
            onClickListener(productModel)
        }
    }
}