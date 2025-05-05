package com.rksrtx76.cinemax

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.rksrtx76.cinemax.presentation.screens.Navigation
import com.rksrtx76.cinemax.presentation.viewmodel.ThemeViewModel
import com.rksrtx76.cinemax.ui.theme.CINEMAXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel = hiltViewModel<ThemeViewModel>()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
            val context = LocalContext.current

            val view = LocalView.current
            SideEffect {
                val window = (context as? Activity)?.window
                window?.let {
                    WindowCompat.setDecorFitsSystemWindows(it, false)
                    val insetsController = WindowInsetsControllerCompat(window, view)
                    insetsController.isAppearanceLightStatusBars = !isDarkTheme
                }
            }

            CINEMAXTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                Navigation(
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    themeViewModel = themeViewModel
                    )
            }
        }
    }
}
