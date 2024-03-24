package com.example.pos_moneylist

import com.example.pos_moneylist.data.productColorList.ProductColorList
import com.example.pos_moneylist.data.productList.ProductList
import com.example.pos_moneylist.data.saleItemList.SaleItemList
import io.paperdb.Paper

/**
 * A Controller provides instances of different classes, which are needed for this app.
 * It is implemented as a singleton. //TODO add singleton pattern with companion object
 * In particular this controller provides instances of [ProductList], [SaleItemList] and [ProductColorList]
 *
 * This version of the controller also provides the functionalities to save and load these instances.
 */
object Controller {

    val productList: ProductList = loadProductList()
    val saleItemList: SaleItemList = SaleItemList()
    val productColorList: ProductColorList = loadProductColorList()

    fun saveProductList() {
        Paper.book().write("productList", productList)
    }

    private fun loadProductList(): ProductList {
        return Paper.book().read("productList") ?: ProductList()
    }

    fun saveProductColorList() {
        Paper.book().write("productColorList", productColorList)
    }

    private fun loadProductColorList(): ProductColorList {
        return Paper.book().read("productColorList") ?: ProductColorList()
    }
}