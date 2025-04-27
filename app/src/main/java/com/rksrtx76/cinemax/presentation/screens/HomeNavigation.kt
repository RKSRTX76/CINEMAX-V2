package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    bottomBarNavController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HOME_SCREEN
    ){
        composable(Screen.HOME_SCREEN){
            HomeScreenContent(
                modifier = Modifier,  // this line
                paddingValues = paddingValues,
                bottomBarNavController = bottomBarNavController,
                scrollBehavior = scrollBehavior,
                homeViewModel = homeViewModel,
                bookMarkViewModel = bookMarkViewModel
            )
        }

        // Todo
    }
}