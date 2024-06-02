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

package com.example.pos_moneylist.ui.productListsScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pos_moneylist.Controller
import com.example.pos_moneylist.data.productList.Product
import com.example.pos_moneylist.data.productList.ProductList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductListsScreenViewModel : ViewModel() {

    val productLists = Controller.productLists

    private val _uiState = MutableStateFlow(
        ProductListsScreenUiState(currProductList = productLists.firstOrNull(),
            currListIndex = 0,
            productListNames = productLists.map { list -> list.name })
    )
    val uiState = _uiState.asStateFlow()

    fun getListIndex(listName: String): Int {
        return productLists.indexOfFirst { list -> list.name == listName }
    }

    fun setCurrentListIndex(listIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currListIndex = listIndex.coerceAtLeast(0), currProductList = try {
                    productLists[listIndex.coerceAtLeast(0)]
                } catch (e: IndexOutOfBoundsException) {
                    null
                }
            )
        }
    }

    fun addList(listName: String): Boolean {
        if (containsList(listName = listName)) {
            return false
        }
        productLists.add(ProductList(listName))
        sortLists()
        _uiState.update { currentState ->
            currentState.copy(productListNames = productLists.map { list -> list.name })
        }
        setCurrentListIndex(getListIndex(listName))
        return true
    }

    fun containsList(listName: String): Boolean {
        return productLists.indexOfFirst { list -> list.name == listName } != -1
    }

    fun removeList(listIndex: Int) {
        productLists.removeAt(listIndex)

        _uiState.update { currentState ->
            currentState.copy(productListNames = productLists.map { list -> list.name })
        }

        setCurrentListIndex(listIndex - 1)
    }

    private fun sortLists() {
        productLists.forEach { list -> list.sortList() }
        productLists.sortWith(compareBy { it.name })
    }

    fun addProduct(
        listIndex: Int,
        product: Product,
    ) {
        productLists[listIndex].apply {
            add(product = product)
            sortList()
        }
    }

    fun containsProduct(
        listIndex: Int,
        product: Product,
    ): Boolean {
        return productLists[listIndex].contains(product = product)
    }

    fun removeProduct(
        listIndex: Int,
        product: Product,
    ) {
        productLists[listIndex].remove(product)
    }

    fun setDetailedProduct(product: Product) {
        _uiState.update { currentState ->
            currentState.copy(detailedProduct = product)
        }
    }

    // flags ---------------------------------------------------------------------------------------
    fun showAddProductScreen() {
        _uiState.update { currentState ->
            currentState.copy(isAddProductScreenVisible = true)
        }
    }

    fun hideAddProductScreen() {
        _uiState.update { currentState ->
            currentState.copy(isAddProductScreenVisible = false)
        }
    }

    fun showProductDetailsScreen() {
        _uiState.update { currentState ->
            currentState.copy(isProductDetailsScreenVisible = true)
        }
    }

    fun hideProductDetailsScreen() {
        _uiState.update { currentState ->
            currentState.copy(isProductDetailsScreenVisible = false)
        }
    }

    fun showAddListScreen() {
        _uiState.update { currentState ->
            currentState.copy(isAddListScreenVisible = true)
        }
    }

    fun hideAddListScreen() {
        _uiState.update { currentState ->
            currentState.copy(isAddListScreenVisible = false)
        }
    }

    fun showEditListScreen() {
        _uiState.update { currentState ->
            currentState.copy(isEditListScreenVisible = true)
        }
    }

    fun hideEditListScreen() {
        _uiState.update { currentState ->
            currentState.copy(isEditListScreenVisible = false)
        }
    }
}

data class ProductListsScreenUiState(
    // data
    val currProductList: ProductList? = null,
    val currListIndex: Int = 0,
    val productListNames: List<String> = emptyList(),
    val detailedProduct: Product = Product("No product", -1f, Color.Black),
    // flags
    val isAddProductScreenVisible: Boolean = false,
    val isProductDetailsScreenVisible: Boolean = false,
    val isAddListScreenVisible: Boolean = false,
    val isEditListScreenVisible: Boolean = false
)
