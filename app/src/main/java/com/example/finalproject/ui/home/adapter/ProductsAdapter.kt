package com.example.finalproject.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.dto.response.ProductType
import com.example.finalproject.databinding.ItemRvHomeProductsBinding
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private var productsList: List<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRvHomeProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProductList: List<Product>) {
        productsList = newProductList
        notifyDataSetChanged()
    }
    fun filtered(productType: ProductType) {

    val filtered = productsList.filter { filt ->
        filt.productType?.idProductType.toString().lowercase().contains(productType.idProductType.toString().lowercase()) }

    updateFilter(filtered)
    Log.i("asd", productsList.toString())
    Log.i("asd", productType.toString())
    Log.i("asd", filtered.toString())
}



    @SuppressLint("NotifyDataSetChanged")
    fun updateFilter(product: List<Product>){
        productsList = product
        notifyDataSetChanged()
    }


    inner class ProductViewHolder(
        private val binding: ItemRvHomeProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.nameRecyclerProduct.text = product.name ?: "No Data"
            binding.tvRecyclerPrice.text = product.price?.toString() ?: "No Data"

            val imageUrl = product.image ?: ""
            if (imageUrl.isNotEmpty()) {
                Log.d("ProductsAdapter", "Cargando imagen desde URL: $imageUrl")

                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.appicon) // Imagen placeholder mientras se carga
                    .error(R.drawable.appicon) // Imagen por defecto en caso de error
                    .into(binding.ivRecyclerProduct)
            } else {
                Log.d("ProductsAdapter", "URL de imagen vacía, mostrando imagen por defecto")
                binding.ivRecyclerProduct.setImageResource(R.drawable.appicon) // Imagen Placeholder
            }
        }
    }
}

