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
                val name = bufferedReader.readLine()
                val link = bufferedReader.readLine()
                val text = bufferedReader.readText()
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