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