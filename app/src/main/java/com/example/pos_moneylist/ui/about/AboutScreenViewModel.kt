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

package com.example.pos_moneylist.ui.about

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class AboutScreenViewModel : ViewModel() {

    var license = mutableStateOf(License())

    fun loadLicenses(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bufferedReader = context.assets.open("licenses/PaperDB").bufferedReader()
                val name = bufferedReader.readLine() // First line of the license is the name
                val link = bufferedReader.readLine() // Seconed line of the license is the link
                val text = bufferedReader.readText() // The rest of the license is the text
                bufferedReader.close()
                launch(Dispatchers.Main) {
                    license.value = license.value.copy(
                        name = name,
                        link = link,
                        text = text,
                    )

                }
            } catch (_: IOException) {

            }
        }
    }

    fun getAppVersion(context: Context): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }
}

data class License(
    val name: String = "",
    val link: String = "",
    val text: String = "",
)