package com.rksrtx76.cinemax.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
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
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Screen
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController,
    bottomBarNavController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val detailsViewModel = hiltViewModel<DetailsViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.HOME_SCREEN
    ){
        composable(Screen.HOME_SCREEN){
            HomeScreenContent(
                modifier = Modifier,  // this line
                paddingValues = paddingValues,
                bottomBarNavController = bottomBarNavController,
                navController = navController,
                scrollBehavior = scrollBehavior,
                homeViewModel = homeViewModel,
                detailsViewModel = detailsViewModel,
                bookMarkViewModel = bookMarkViewModel
            )
        }

//        composable(
//            "${Screen.DETAILS_SCREEN}?id={id}&type={type}",
//            arguments = listOf(
//                navArgument("id") { type = NavType.IntType },
//                navArgument("type") { type = NavType.StringType }
//            )
//        ) {
//            val id = it.arguments?.getInt("id") ?: 0
//            val type = it.arguments?.getString("type") ?: ""
//            Timber.d("Navigation: $id $type")
//
//            DetailScreen(
//                lifecycleOwner = lifecycleOwner,
//                mediaId = id,
//                mediaType = type,
//                navController = navController,
//                detailsViewModel = detailsViewModel,
//                homeViewModel = homeViewModel,
//                paddingValues = paddingValues
//            )
//        }


    }
}