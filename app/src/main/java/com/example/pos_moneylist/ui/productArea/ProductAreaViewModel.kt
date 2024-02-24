package com.example.pos_moneylist.ui.productArea

import androidx.lifecycle.ViewModel
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.data.productList.Product

class ProductAreaViewModel : ViewModel() {
    val productList = Controller.productList

    fun addProduct(product: Product) {
        productList.add(product)
    }
}

