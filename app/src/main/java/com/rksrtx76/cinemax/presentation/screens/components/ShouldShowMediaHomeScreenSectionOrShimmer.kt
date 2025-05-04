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
import com.rksrtx76. CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants
import timber.log.Timber

@Composable
fun ShouldShowMediaHomeScreenSectionOrShimmer(
    type  :String,
    showShimmer :Boolean,
    pagingItems : LazyPagingItems<Media>,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    detailsViewModel : DetailsViewModel,
    navController: NavController,
    bottomBarNavController: NavController
) {
    val title = getTitle(type)
    Timber.d("Shimmer: $type")

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
            mediaItems = pagingItems,
            modifier = modifier,
            homeViewModel = homeViewModel,
            detailsViewModel = detailsViewModel,
            navController = navController,
            bottomNavController = bottomBarNavController
        )
    }

}