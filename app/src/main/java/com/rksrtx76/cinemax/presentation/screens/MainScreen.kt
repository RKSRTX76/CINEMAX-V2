package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rksrtx76.cinemax.presentation.screens.components.BottomNavigationBar
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.SearchViewModel
import com.rksrtx76.cinemax.util.BottomNav
import com.rksrtx76.cinemax.util.Screen

@Composable
fun MainScreen(
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    bookMarkViewModel: BookMarkViewModel,
    detailsViewModel: DetailsViewModel,
    navController: NavController
){

    val selectedItem = rememberSaveable { mutableStateOf(0) }
    val bottomNavController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                bottomNavController = bottomNavController
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.HOME_SCREEN
        ){
            composable(BottomNav.HOME_SCREEN) {
                HomeScreen(
                    bottomBarNavController = bottomNavController,
                    paddingValues = paddingValues,
                    navController = navController
                )
            }
            composable(BottomNav.SEARCH_SCREEN){
                SearchScreen(
                    selectedItem = selectedItem,
                    navController = navController, // important: use root for detail nav
                    searchViewModel = searchViewModel,
                    homeViewModel = homeViewModel,
                    paddingValues = paddingValues
                )
            }
            composable(BottomNav.BOOKMARK_SCREEN) {
                MediaListScreen(
                    navController = navController,
                    bookMarkViewModel = bookMarkViewModel,
                    paddingValues = paddingValues
                )
            }
        }
    }
}