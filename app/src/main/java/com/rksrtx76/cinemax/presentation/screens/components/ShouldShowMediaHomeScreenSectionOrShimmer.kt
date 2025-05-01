package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants

@Composable
fun ShouldShowMediaHomeScreenSectionOrShimmer(
    type  :String,
    showShimmer :Boolean,
    pagingItems : LazyPagingItems<Media>,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavController,
    navHostController: NavHostController
) {
    val title = getTitle(type)

    if(showShimmer){
        // if list empty then show shimmer only (no data)
        ShowHomeShimmer(
            title = title,
            modifier = modifier
//                .height(195.dp)
//                .width(125.dp)
//                .padding(top = 20.dp, bottom = 12.dp)
        )
    }else{
        MediaHomeScreenSection(
            title = title,
            type = type,
            mediaItems = pagingItems,
            modifier = modifier,
            homeViewModel = homeViewModel,
            navController = navController,
            bottomNavController = navHostController
        )
    }

}