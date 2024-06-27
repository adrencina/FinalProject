package com.example.finalproject.ui.home.recycler.adapter.rvSearchs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.model.Product

class SearchAdapter(
    private var productLst: List<Product>,
    private val onClickListener:(Product) -> Unit

) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(layoutInflater.inflate(R.layout.item_rv_home_search_list, parent,false))
    }

    override fun getItemCount(): Int {
        return productLst.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = productLst[position]
        holder.render(item, onClickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(productLst:List<Product>){
        this.productLst = productLst
        notifyDataSetChanged()
    }
}