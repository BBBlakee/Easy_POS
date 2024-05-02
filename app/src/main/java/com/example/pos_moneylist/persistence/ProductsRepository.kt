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

package com.example.pos_moneylist.persistence

import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.data.productList.ProductList

/**
 * Repository that provides insert, update, delete and retrieve
 * of products from a give data source
 */
interface ProductsRepository {

    /**
     * Insert item into data source
     */
    suspend fun insertProduct(product: Product)

    /**
     * Delete item from the data source
     */
    suspend fun deleteProduct(product: Product)

    /**
     * Retrieves all products from the data source
     */
    fun loadProductLists(callback: () -> Unit = {}): List<ProductList>

    suspend fun loadProductList(
        name: String,
        callback: () -> Unit = {},
    ): ProductList

    fun saveLists(
        lists: List<ProductList>,
        callback: () -> Unit = {},
    )

    suspend fun saveList(
        list: ProductList,
        callback: () -> Unit = {},
    )
}