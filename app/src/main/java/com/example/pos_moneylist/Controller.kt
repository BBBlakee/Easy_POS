/*
 *     The app is a simple point of sale system, mainly developed for small clubs without a
 *     point of sale system. It was developed to simplify the calculation of the total price.
 *
 *     Copyright (C) 2024 Michael Gamperling
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.example.pos_moneylist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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

    val productLists: SnapshotStateList<ProductList> = loadProductList()
    val saleItemList: SaleItemList = SaleItemList()
    val productColorList: ProductColorList = loadProductColorList()

    fun saveProductLists() {
        productLists.forEach { list ->
            Paper.book("products").write(list.name, list)
        }
    }

    private fun loadProductList(): SnapshotStateList<ProductList> {
        val keys = Paper.book("products").allKeys
        val list: SnapshotStateList<ProductList> = mutableStateListOf()

        keys.forEach { key ->
            list.add(Paper.book("products").read(key) ?: ProductList("test"))
        }

        return list
    }

    fun deleteProductList(listName: String) {
        Paper.book("products").delete(listName)
    }

    fun saveProductColorList() {
        Paper.book("extra").write("productColorList", productColorList)
    }

    private fun loadProductColorList(): ProductColorList {
        return Paper.book("extra").read("productColorList") ?: ProductColorList()
    }
}