package com.example.pos_moneylist.persistence

import com.example.pos_moneylist.data.productList.Product

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
     * Update item in the data source
     */
    suspend fun updateProduct(product: Product)

    /**
     * Delete item from the data source
     */
    suspend fun deleteProduct(product: Product)

    /**
     * Retrieves all products from the data source
     */
    suspend fun loadProductList()
}