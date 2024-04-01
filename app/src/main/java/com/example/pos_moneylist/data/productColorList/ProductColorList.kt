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