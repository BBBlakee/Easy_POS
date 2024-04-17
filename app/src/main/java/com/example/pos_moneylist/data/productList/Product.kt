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

package com.example.pos_moneylist.data.productList

import androidx.compose.ui.graphics.Color

/**
 * Standard representation of a product object
 * @param name Name of the product
 * @param price Price of the product
 * @param color Color is used for the background color of the product button
 */
data class Product(
    var name: String,
    var price: Float,
    var color: Color = Color.Unspecified,
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