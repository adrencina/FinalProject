package com.example.finalproject.ui.leftbar.fragments.financing.adacter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.dto.model.PaymentMethod
import com.example.finalproject.databinding.ItemRvFgFinancingItemBinding
import com.squareup.picasso.Picasso

class FinancingAdapter(
    private var paymentMethods: List<PaymentMethod>
) : RecyclerView.Adapter<FinancingAdapter.FinancingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancingViewHolder {
        val binding = ItemRvFgFinancingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FinancingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinancingViewHolder, position: Int) {
        val payment = paymentMethods[position]
        holder.bind(payment)
    }

    override fun getItemCount(): Int = paymentMethods.size

    fun updateData(newProductList: List<PaymentMethod>) {
        Log.i("DATA update",newProductList.toString())
        paymentMethods = newProductList
        notifyDataSetChanged()
    }

    fun imageReturn(name:String): Int {
        return when(name){
            "Galicia" -> R.drawable.galicia
            "Santander" -> R.drawable.santander
            else -> {
                R.drawable.otros
            }
        }
    }

    inner class FinancingViewHolder(private val binding: ItemRvFgFinancingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentMethod: PaymentMethod) {
            Log.i("DATA", paymentMethod.toString())

            if(paymentMethod.entity == "Otros"){
                binding.titleCardPayment.text = paymentMethod.entity
                val layoutParams = binding.imageView.layoutParams
                layoutParams.width = 40 // Ajusta el valor según tus necesidades
                binding.imageView.layoutParams = layoutParams
            }else{
                binding.titleCardPayment.text = ""
            }

            if (paymentMethod.entity == "Galicia"){
                binding.constraintLayout.background = ContextCompat.getDrawable(binding.root.context, R.drawable.item_fg_bg_orange)
            }

            Picasso.get()
                .load(imageReturn(paymentMethod.entity))
                .into(binding.imageView)

            "${paymentMethod.installments[0].quantity} cuotas:".also { binding.textfinancing1.text = it };
            binding.textfinancing5.text=paymentMethod.installments[0].quantity.toString();

            "${paymentMethod.installments[1].quantity} cuotas:".also { binding.textfinancing2.text = it };
            binding.textfinancing6.text=paymentMethod.installments[1].quantity.toString();

            "${paymentMethod.installments[2].quantity} cuotas:".also { binding.textfinancing3.text = it };
            binding.textfinancing7.text=paymentMethod.installments[2].quantity.toString();

            "${paymentMethod.installments[3].quantity} cuotas:".also { binding.textfinancing4.text = it };
            binding.textfinancing8.text=paymentMethod.installments[3].quantity.toString();
        }
    }
}
