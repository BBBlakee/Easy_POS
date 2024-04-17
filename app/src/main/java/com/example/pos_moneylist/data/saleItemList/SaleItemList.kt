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

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class SaleItemList : Iterable<SaleItem> {
    var saleItemList: SnapshotStateList<SaleItem> = mutableStateListOf()
    var total: MutableState<Float> = mutableFloatStateOf(0.0f)

    fun add(saleItem: SaleItem) {
        val i: Int = saleItemList.indexOfFirst { it.name == saleItem.name }
        if (i != -1) {
            saleItemList[i].increment()
        } else {
            saleItem.increment()
            saleItemList.add(saleItem)
        }
        total.value += saleItem.price
    }

    fun remove(saleItem: SaleItem) {
        saleItem.decrement()
        if (saleItem.getAmount() == 0) {
            saleItemList.remove(saleItem)
            //saleItemList.removeAt(saleItemList.indexOf(saleItem))
        }
        total.value -= saleItem.price
    }

    fun removeAll() {
        saleItemList.clear()
        total.value = 0.0f
    }

    override fun iterator(): Iterator<SaleItem> {
        return saleItemList.iterator()
    }

    override fun toString(): String {
        var output = "\n--------------"
        for (item in saleItemList) {
            output += "\n${item.name}\t# ${item.getAmount()} : " +
                    "Preis = ${item.price} SumAnzahl*Preis = ${item.getTotalPrice()}"
        }
        output += "\n\nGesamtpreis = ${total.value}"
        output += "\n--------------\n"
        return output
    }
}