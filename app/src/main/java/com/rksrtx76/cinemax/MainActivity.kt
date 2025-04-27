package com.rksrtx76.cinemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.presentation.Navigation
import com.rksrtx76.cinemax.presentation.screens.HomeScreen
import com.rksrtx76.cinemax.presentation.screens.MediaListScreen
import com.rksrtx76.cinemax.presentation.screens.components.BottomNavigationBar
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.CINEMAXTheme
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.BottomNav
import com.rksrtx76.cinemax.util.Screen
import com.rksrtx76.cinemax.util.items
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.emptyFlow

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
                    Navigation(bottomNavController = bottomNavController, paddingValues = paddingValues)
                }
            }
        }
    }
}



