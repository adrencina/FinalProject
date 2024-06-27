package com.example.finalproject.ui.home.recycler.adapter.rvProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.model.Product


class ProductAdapter(
    private val productLst:List<Product>,
    private val onClickListener:(Product) -> Unit

) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_rv_home_products, parent,false))
    }

    override fun getItemCount(): Int {
        return productLst.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productLst[position]
        holder.render(item, onClickListener)
    }
}