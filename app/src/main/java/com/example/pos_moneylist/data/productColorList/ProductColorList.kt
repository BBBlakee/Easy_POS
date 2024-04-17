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

package com.example.pos_moneylist.data.productColorList

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

class ProductColorList : Iterable<Color> {
    val productColorList: SnapshotStateList<Color> = mutableStateListOf()

    init {
        productColorList.addAll(
            listOf(
                Color(0xFFFCEC42),
                Color(0xFF18641B),
                Color(0xFF65ECFD),
                Color(0xFFF9ADFF),
                Color(0xFFB0FFED),
                Color(0xFFFF9800),
                Color(0xFFFCF196),
                Color(0xFF33E8FF),
                Color(0xFF0064B4),
                Color(0xFFFDCD3E),
                Color(0xFFFDDFCA),
                Color(0xFFE60000),
                Color(0xFF8BC34A),
                Color(0xFF6D0080),
                Color(0xFFFFFFFF)
            )
        )
    }

    fun addColor(color: Color): Boolean {
        if (productColorList.contains(color)) {
            return false
        }
        return productColorList.add(color)
    }

    fun contains(color: Color): Boolean {
        return productColorList.contains(color)
    }

    override fun iterator(): Iterator<Color> {
        return productColorList.iterator()
    }
}