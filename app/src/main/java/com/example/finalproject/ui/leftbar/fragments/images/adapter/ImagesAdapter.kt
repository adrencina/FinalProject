package com.example.finalproject.ui.leftbar.fragments.images.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.model.Image
import com.example.finalproject.databinding.ItemRvImgfragmentBinding
import com.squareup.picasso.Picasso

class ImagesAdapter(private val images: List<Image>) : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ItemRvImgfragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ImagesViewHolder(private val binding: ItemRvImgfragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            Picasso.get()
                .load(image.link)
                .placeholder(R.drawable.imgerror) // Placeholder image
                .error(R.drawable.errornotphoto)  // Error image
                .into(binding.ivProduct)
        }
    }
}
