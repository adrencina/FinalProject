package com.example.finalproject.ui.leftbar.fragments.financing.adacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.model.PaymentMethod
import com.example.finalproject.databinding.ItemRvFgFinancingItemBinding


class FinancingAdapter (
    private var paymentMethods: List<PaymentMethod>
) : RecyclerView.Adapter<FinancingAdapter.FinancingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancingViewHolder{
        val binding = ItemRvFgFinancingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FinancingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinancingViewHolder, position: Int) {
        val product = paymentMethods[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = paymentMethods.size

    fun updateData(newProductList: List<PaymentMethod>) {
        paymentMethods = newProductList
    }


    inner class FinancingViewHolder(private val binding: ItemRvFgFinancingItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentMethod: PaymentMethod) {
            binding.textView3.text = paymentMethod.entity
        }
    }
}



