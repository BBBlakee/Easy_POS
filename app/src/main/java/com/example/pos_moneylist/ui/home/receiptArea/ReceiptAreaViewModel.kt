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

package com.example.pos_moneylist.ui.home.receiptArea

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