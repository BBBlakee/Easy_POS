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

package com.example.pos_moneylist.data.saleItemList

import com.example.pos_moneylist.data.productList.Product

data class SaleItem(val saleProduct: Product) {
    var counter: Int = 0
    val name: String = saleProduct.name
    val price: Float = saleProduct.price


    fun getTotalPrice(): Float {
        return saleProduct.price * counter
    }

    fun getAmount(): Int {
        return counter
    }

    fun increment() {
        counter++
    }

    fun decrement() {
        counter--
    }

    /**
     * SaleItems are equal if they have the same name.
     * Price and Amount are not compared
     */
    override fun equals(other: Any?): Boolean {
        if (other is SaleItem) {
            return hashCode() == other.hashCode()
        }
        return false
    }

    /**
     * Hashcode is generated only for the name,
     * because price and amount are not relevant for comparison
     */
    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return String.format("%-15.5s # %d", name, counter)
    }

}