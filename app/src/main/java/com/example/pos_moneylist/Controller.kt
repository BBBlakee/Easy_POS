package com.example.pos_moneylist

import com.example.pos_moneylist.data.productList.ProductList
import com.example.pos_moneylist.data.saleItemList.SaleItemList
import io.paperdb.Paper

object Controller {

    val productList: ProductList = loadProductList()
    val saleItemList: SaleItemList = SaleItemList()

    fun saveProductList() {
        Paper.book().write("productList", productList)
    }

    private fun loadProductList(): ProductList {
        return Paper.book().read("productList") ?: ProductList()
    }
}