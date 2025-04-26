package com.rksrtx76.cinemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.presentation.screens.HomeScreen
import com.rksrtx76.cinemax.presentation.screens.MediaListScreen
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.CINEMAXTheme
import com.rksrtx76.cinemax.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.emptyFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CINEMAXTheme {
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val bookMarkViewModel = hiltViewModel<BookMarkViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.MAIN_SCREEN
    ){
        composable(Screen.MAIN_SCREEN){
            HomeScreen(
                navController = navController,
                modifier = Modifier,
                bottomBarNavController = navController,
                homeViewModel = homeViewModel,
                bookMarkViewModel = bookMarkViewModel
            )
        }

        composable(
            route = "${Screen.MEDIA_LIST_SCREEN}/{type}/{title}",
            arguments = listOf(
                navArgument("type"){ type = NavType.StringType},
                navArgument("title"){ type = NavType.StringType},
            )
        ){ navBackStackEntry ->
            // extract data from route
            val type = navBackStackEntry.arguments?.getString("type") ?: ""
            val category = navBackStackEntry.arguments?.getString("title") ?: ""

            val mediaItems  = when(type){
                stringResource(R.string.trending) -> homeViewModel.trendingMedia.value.collectAsLazyPagingItems()
                stringResource(R.string.popular) -> homeViewModel.popularMedia.value.collectAsLazyPagingItems()
                stringResource(R.string.top_rated) -> homeViewModel.topRatedMedia.value.collectAsLazyPagingItems()
                stringResource(R.string.now_playing) -> homeViewModel.nowPlayingMedia.value.collectAsLazyPagingItems()
                else -> homeViewModel.upcomingMovies.value.collectAsLazyPagingItems()   // upcoming movies
            }
            MediaListScreen(
                type = type,
                category = category,
                mediaItems = mediaItems,
                navController = navController,
                navBackStackEntry = navBackStackEntry
            )
        }
    }
}

