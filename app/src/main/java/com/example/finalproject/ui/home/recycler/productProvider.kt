package com.example.finalproject.ui.home.recycler

import com.example.finalproject.R
import com.example.finalproject.data.dto.model.Product


class productProvider {
    companion object{
        val productLst = listOf<Product>(
            Product(R.drawable.remera,"remera","$50","Buzo canguro capucha con cordon"),
            Product(R.drawable.remera,"remera2","$100","Buzo canguro capucha con cordon"),
            Product(R.drawable.remera,"remera3","$40","Buzo canguro capucha con cordon"),
            Product(R.drawable.remera,"remera4","$10","Buzo canguro capucha con cordon"),
        )
    }
}