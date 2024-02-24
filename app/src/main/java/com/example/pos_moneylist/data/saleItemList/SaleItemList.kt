package com.example.pos_moneylist.data.saleItemList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class SaleItemList : Iterable<SaleItem> {
    var saleItemList: SnapshotStateList<SaleItem> = mutableStateListOf()
    var total: MutableState<Float> = mutableStateOf(0.0f)

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