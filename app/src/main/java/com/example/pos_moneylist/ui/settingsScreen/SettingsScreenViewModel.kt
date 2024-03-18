package com.example.pos_moneylist.ui.settingsScreen

import androidx.lifecycle.ViewModel
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.data.productList.Product

class SettingsScreenViewModel : ViewModel() {
    val productList = Controller.productList

    fun addProduct(product: Product) {
        productList.add(product)
        Controller.saveProductList()
    }

    fun contains(product: Product): Boolean {
        return productList.contains(product = product)
    }

    fun remove(product: Product) {
        productList.remove(product)
    }
}