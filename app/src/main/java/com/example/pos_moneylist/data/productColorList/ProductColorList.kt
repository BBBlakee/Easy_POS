package com.example.pos_moneylist.data.productColorList

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

class ProductColorList : Iterable<Color> {
    val productColorList: SnapshotStateList<Color> = mutableStateListOf()

    init {
        productColorList.addAll(
            listOf(
                Color.Red,
                Color.Blue,
                Color.Green,
                Color.Yellow,
                Color.Cyan,
                Color.Magenta
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