package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rksrtx76.cinemax.presentation.screens.details.DetailScreen
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.SearchViewModel
import com.rksrtx76.cinemax.util.BottomNav
import com.rksrtx76.cinemax.util.Screen
import timber.log.Timber


@Composable
fun Navigation(
    selectedItem  : MutableState<Int>,
    bottomNavController: NavHostController,
    paddingValues: PaddingValues
){
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val navController = rememberNavController()
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNav.MEDIA_MAIN_SCREEN
    ){
        composable(BottomNav.MEDIA_MAIN_SCREEN){
            HomeScreen(
                bottomBarNavController = bottomNavController,
                paddingValues = paddingValues
            )
        }
        composable(BottomNav.SEARCH_SCREEN){ navBackStackEntry ->
            SearchScreen(
                bottomNavController = bottomNavController,
                selectedItem = selectedItem,
                navBackStackEntry = navBackStackEntry,
                navController = bottomNavController,
                searchViewModel = searchViewModel,
                homeViewModel = homeViewModel,
                paddingValues = paddingValues
            )
        }

        composable(
            "${Screen.DETAILS_SCREEN}?id={id}&type={type}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            val type = it.arguments?.getString("type") ?: ""
            Timber.d("Navigation: $id $type")

            DetailScreen(
                lifecycleOwner = lifecycleOwner,
                mediaId = id,
                mediaType = type,
                navController = navController,
                detailsViewModel = detailsViewModel
            )
        }
        composable(BottomNav.BOOKMARK_SCREEN){
//            BookMarkScreen()
        }
        composable(BottomNav.PROFILE_SCREEN){
//            ProfileScreen()
        }

    }
}