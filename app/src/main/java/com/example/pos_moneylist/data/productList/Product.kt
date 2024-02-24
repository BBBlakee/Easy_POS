package com.example.pos_moneylist.data.productList

import androidx.compose.ui.graphics.Color

/**
 * Standard representation of a product object
 * @param name Name of the product
 * @param price Price of the product
 * @param color Color is used for the background color of the product button
 */
data class Product(
    val name: String,
    var price: Float,
    val color: Color,
) {

    /**
     * Products are equal if they have the same name.
     * Price and Color are not compared
     */
    override fun equals(other: Any?): Boolean {
        if (other is Product) {
            return hashCode() == other.hashCode()
        }
        return false
    }

    /**
     * Hashcode is generated only for the name,
     * because price and color are not necessary for comparison
     */
    override fun hashCode(): Int {
        return name.hashCode()
    }
}