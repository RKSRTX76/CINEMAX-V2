package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rksrtx76.cinemax.presentation.screens.details.CastListScreen
import com.rksrtx76.cinemax.presentation.screens.details.DetailScreen
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.SearchViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.ThemeViewModel
import com.rksrtx76.cinemax.util.Screen
import timber.log.Timber


@Composable
fun Navigation(
    navController : NavHostController,
    isDarkTheme : Boolean,
    themeViewModel: ThemeViewModel
){
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val bookMarkViewModel = hiltViewModel<BookMarkViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH_SCREEN
    ){

        composable(Screen.SPLASH_SCREEN){
            SplashScreen(navController = navController)
        }
        composable(Screen.MAIN_SCREEN){
            MainScreen(
                homeViewModel = homeViewModel,
                searchViewModel = searchViewModel,
                bookMarkViewModel = bookMarkViewModel,
                detailsViewModel = detailsViewModel,
                navController = navController,
                isDarkTheme = isDarkTheme,
                themeViewModel = themeViewModel
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

            LaunchedEffect(id, type) {
                val result = if(type == "movie") {
                    detailsViewModel.getMovieDetails(id)
                    detailsViewModel.getMovieCastDetails(id)
                    homeViewModel.getSimilarMovies(id)
                } else {
                    detailsViewModel.getSeriesDetails(id)
                    detailsViewModel.getSeriesCastDetails(id)
                    homeViewModel.getSimilarSeries(id)
                }
            }

            DetailScreen(
                lifecycleOwner = lifecycleOwner,
                mediaId = id,
                mediaType = type,
                navController = navController,
                detailsViewModel = detailsViewModel,
                homeViewModel = homeViewModel,
            )
        }

        composable(
            "${Screen.CAST_LIST_SCREEN}?castId={id}&type={type}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType},
                navArgument("type") { type = NavType.StringType}
            )
        ){
            val id = it.arguments?.getInt("id") ?: 0
            val type = it.arguments?.getString("type") ?: ""

            CastListScreen(
                detailsViewModel = detailsViewModel,
                type = type,
            )
        }

    }
}