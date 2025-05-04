package com.rksrtx76.cinemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.rksrtx76.cinemax.presentation.screens.Navigation
import com.rksrtx76.cinemax.presentation.screens.components.BottomNavigationBar
import com.rksrtx76.cinemax.ui.theme.CINEMAXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CINEMAXTheme {
                val bottomNavController = rememberNavController()
                val selectedItem = rememberSaveable { mutableStateOf(0) }
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            selectedItem = selectedItem,
                            bottomNavController = bottomNavController
                        )
                    }
                ) { paddingValues ->
                    Navigation(
                        paddingValues = paddingValues,
                        selectedItem = selectedItem,
                        bottomNavController = bottomNavController,
                    )
                }
            }
        }
    }
}



