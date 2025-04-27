package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.BottomNav


@Composable
fun Navigation(
    bottomNavController: NavHostController,
    paddingValues: PaddingValues
){
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val bookmarkViewModel = hiltViewModel<BookMarkViewModel>()
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNav.MEDIA_MAIN_SCREEN
    ){
        composable(BottomNav.MEDIA_MAIN_SCREEN){
            HomeScreen(
                bottomBarNavController = bottomNavController,
                homeViewModel = homeViewModel,
                bookMarkViewModel = bookmarkViewModel,
                paddingValues = paddingValues
            )
        }
        composable(BottomNav.SEARCH_SCREEN){
//            SearchScreen()
        }
        composable(BottomNav.BOOKMARK_SCREEN){
//            BookMarkScreen()
        }
        composable(BottomNav.PROFILE_SCREEN){
//            ProfileScreen()
        }

    }
}