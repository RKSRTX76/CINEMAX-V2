package com.rksrtx76.cinemax.presentation.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.screens.components.GenreSelectable
import com.rksrtx76.cinemax.presentation.screens.components.ShouldShowMediaHomeScreenSectionOrShimmer
import com.rksrtx76.cinemax.presentation.screens.components.TopAppBar
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants
import com.rksrtx76.cinemax.util.Constants.NOW_PLAYING
import com.rksrtx76.cinemax.util.Constants.popularScreen
import com.rksrtx76.cinemax.util.Constants.recommendedListScreen
import com.rksrtx76.cinemax.util.Constants.topRatedAllListScreen
import com.rksrtx76.cinemax.util.Constants.trendingAllListScreen
import com.rksrtx76.cinemax.util.Constants.upcomingMoviesScreen
import com.rksrtx76.cinemax.util.MediaType

@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    navController: NavController,
    bottomBarNavController : NavHostController,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel
){
    var tabPage by remember { mutableStateOf(MediaType.MOVIE) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                homeViewModel = homeViewModel,
                tabPage = tabPage,
                onTabSelected = { selectedTab->
                    tabPage = selectedTab
                    if(homeViewModel.selectedMediaType.value != selectedTab){
                        homeViewModel.selectedMediaType.value = selectedTab
                        homeViewModel.getMediaGenre()
                        homeViewModel.refreshAll(null)
                    }
                },
                modifier = modifier
            )
        },
    ) { padding->
        HomeScreenContent(
            modifier = Modifier.padding(top = padding.calculateTopPadding()),  // this line
            navController = navController,
            bottomBarNavController = bottomBarNavController,
            homeViewModel = homeViewModel,
            bookMarkViewModel = bookMarkViewModel
        )
    }
}



@Composable
fun HomeScreenContent(
    modifier : Modifier = Modifier,
    navController: NavController,
    bottomBarNavController : NavHostController,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel
){
    val context = LocalContext.current
    BackHandler(enabled = true) {
        (context as Activity).finish()
    }

    val trendingMedia = homeViewModel.trendingMedia.value.collectAsLazyPagingItems()
    val popularMedia = homeViewModel.popularMedia.value.collectAsLazyPagingItems()
    val topRatedMedia = homeViewModel.topRatedMedia.value.collectAsLazyPagingItems()
    val nowPlayingMedia = homeViewModel.nowPlayingMedia.value.collectAsLazyPagingItems()
    val upcomingMovie = homeViewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val recommendedMedia = homeViewModel.recommendedMedia.value.collectAsLazyPagingItems()
    val selectedMediaType = homeViewModel.selectedMediaType.value
    val bookmarkMedia = bookMarkViewModel.bookMarkList.value.collectAsState(initial = emptyList())

    val listState = rememberLazyListState()


    // at launch setup recommended movies based on bookmark list
    LaunchedEffect(bookmarkMedia.value.size) {
        if(bookmarkMedia.value.isNotEmpty()){
            // pick a random media id from bookmark list
            homeViewModel.randomMediaId = bookmarkMedia.value[
                (0..bookmarkMedia.value.lastIndex).random()
            ].mediaId

            if(recommendedMedia.itemCount == 0){
                homeViewModel.getRecommendedMedia(mediaId = homeViewModel.randomMediaId!!)
            }
        }
    }

    Box(
        modifier = modifier
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
//                .padding(top = 26.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
//            contentPadding = PaddingValues(vertical = 12.dp)
        ) {

            item {
                val genres = homeViewModel.mediaGenre
                val selectedGenre = homeViewModel.selectedGenre
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(genres.size){ idx->
                        val genre = genres[idx]
                        GenreSelectable(
                            genre = genre.name,
                            selected = genre.name == selectedGenre.value.name,
                            onClick = {
                                if(selectedGenre.value.name != genre.name){
                                    homeViewModel.selectedGenre.value = genre
                                    homeViewModel.filterBySetSelectedGenre(genre = genre)
                                }
                            }
                        )
                    }
                }
            }

            item{
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = trendingAllListScreen,
                    showShimmer = trendingMedia.itemCount == 0,
                    pagingItems = trendingMedia,
                    modifier = modifier,
                    navController = navController,
                    navHostController = bottomBarNavController,
                )
            }

            item{
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = popularScreen,
                    showShimmer = popularMedia.itemCount == 0,
                    pagingItems = popularMedia,
                    modifier = modifier,
                    navController = navController,
                    navHostController = bottomBarNavController,
                )
            }
            item{
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = topRatedAllListScreen,
                    showShimmer = topRatedMedia.itemCount == 0,
                    pagingItems = topRatedMedia,
                    modifier = modifier,
                    navController = navController,
                    navHostController = bottomBarNavController,
                )
            }
            item{
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = NOW_PLAYING,
                    showShimmer = nowPlayingMedia.itemCount == 0,
                    pagingItems = nowPlayingMedia,
                    modifier = modifier,
                    navController = navController,
                    navHostController = bottomBarNavController,
                )
            }
            // UI basis lock
            if(selectedMediaType == MediaType.MOVIE){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = upcomingMoviesScreen,
                        showShimmer = upcomingMovie.itemCount == 0,
                        pagingItems = upcomingMovie,
                        modifier = modifier,
                        navController = navController,
                        navHostController = bottomBarNavController,
                    )
                }
            }

            if(recommendedMedia.itemCount != 0){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = recommendedListScreen,
                        showShimmer = recommendedMedia.itemCount == 0,
                        pagingItems = recommendedMedia,
                        modifier = modifier,
                        navController = navController,
                        navHostController = bottomBarNavController,
                    )
                }
            }
        }
    }

}