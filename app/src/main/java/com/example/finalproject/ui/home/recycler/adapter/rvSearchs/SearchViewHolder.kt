package com.example.finalproject.ui.home.recycler.adapter.rvSearchs

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finalproject.data.dto.model.Product
import com.example.finalproject.databinding.ItemRvHomeSearchListBinding
import com.squareup.picasso.Picasso



class SearchViewHolder(view: View): ViewHolder(view) {

    val binding = ItemRvHomeSearchListBinding.bind(view)

    fun render(productModel: Product, onClickListener:(Product) -> Unit){
        binding.tvSeachTitle.text = productModel.name
        binding.tvSearchSubtitle.text = productModel.subtitle
        binding.tvSearchPrice.text = productModel.price
        Picasso.get().load(productModel.photo).into(binding.ivSearchPhoto)
        itemView.setOnClickListener {
            onClickListener(productModel)
        }
    }
}