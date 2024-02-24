package com.example.pos_moneylist.ui.receiptArea

import androidx.lifecycle.ViewModel
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.data.saleItemList.SaleItem

class ReceiptAreaViewModel : ViewModel() {
    val saleItemList = Controller.saleItemList

    fun addSaleItem(saleItem: SaleItem) {
        saleItemList.add(saleItem)
    }

    fun removeSaleItem(saleItem: SaleItem) {
        saleItemList.remove(saleItem)
    }

    fun clear() {
        saleItemList.removeAll()
    }
}