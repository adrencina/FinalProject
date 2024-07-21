package com.example.finalproject.ui.home.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.databinding.ItemRvHomeTextProductBinding
class ProductTypesAdapter(
    private var productTypes: List<ProductType>,
    private val onClickListener:(ProductType) -> Unit
) :
    RecyclerView.Adapter<ProductTypesAdapter.ProductTypesViewHolder>() {

    class ProductTypesViewHolder(private val binding: ItemRvHomeTextProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productType: ProductType,
            onClickListener: (ProductType) -> Unit

        ) {
            binding.tvProductTypeName.text = productType.description
            itemView.setOnClickListener { onClickListener(productType) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypesViewHolder {
        val binding = ItemRvHomeTextProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductTypesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductTypesViewHolder, position: Int) {
        val item = productTypes[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = productTypes.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProductTypes: List<ProductType>) {
        productTypes = newProductTypes
        notifyDataSetChanged()
    }

}




