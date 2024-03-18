package com.example.pos_moneylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pos_moneylist.navigation.MoneyListNavHost
import com.example.pos_moneylist.ui.theme.POS_MoneyListTheme
import io.paperdb.Paper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POS_MoneyListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Paper.init(applicationContext)
                    Start()

                }
            }
        }
    }
}

@Composable
fun Start(
    navHostController: NavHostController = rememberNavController(),
) {
    MoneyListNavHost(navController = navHostController)
}