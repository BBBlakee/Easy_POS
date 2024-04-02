package com.example.pos_moneylist.data.productList

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * A productList manages all added products
 * @property productList List of all added products
 * @property length Amount of products in the list.
 */
class ProductList : Iterable<Product> {
    var productList: SnapshotStateList<Product> = mutableStateListOf()
    val length: Int
        get() = productList.count()

    /**
     * Adds a product to the list if its not already exists.
     * @param product Product to be added
     * @return Returns true if product does not yet exist and could be added to the list.
     * Returns false if the product already exists
     */
    fun add(product: Product): Boolean {
        if (productList.contains(product)) {
            return false
        }
        return productList.add(product)
    }

    fun contains(product: Product): Boolean {
        return productList.contains(product)
    }

    /**
     * Removes product from the list
     * @param product Product to be removed
     * @return Returns true if product could be removed from the list
     */
    fun remove(product: Product): Boolean {
        return productList.remove(product)
    }

    fun sortList() {
        productList.sortWith(compareBy { it.name })
    }

    /**
     * Returns an iterator of the internal array list
     * @return Returns iterator
     */
    override fun iterator(): Iterator<Product> {
        return productList.iterator()
    }


}