package com.rksrtx76.cinemax.presentation.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.presentation.screens.components.GenreSelectable
import com.rksrtx76.cinemax.presentation.screens.components.ShouldShowMediaHomeScreenSectionOrShimmer
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants.AIRING
import com.rksrtx76.cinemax.util.Constants.MOVIE_TAB
import com.rksrtx76.cinemax.util.Constants.NOW_PLAYING
import com.rksrtx76.cinemax.util.Constants.POPULAR
import com.rksrtx76.cinemax.util.Constants.RECOMMENDED
import com.rksrtx76.cinemax.util.Constants.TOP_RATED
import com.rksrtx76.cinemax.util.Constants.TRENDING
import com.rksrtx76.cinemax.util.Constants.TV_SHOW_TAB
import com.rksrtx76.cinemax.util.Constants.UPCOMING
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier : Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    bottomBarNavController : NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    homeViewModel: HomeViewModel,
    detailsViewModel: DetailsViewModel,
    bookMarkViewModel: BookMarkViewModel
){


    val listState = rememberLazyListState()

    val bookmarkedList = bookMarkViewModel.bookMarkList.value.collectAsState(emptyList())
    val recommendedMovies = homeViewModel.recommendedMovies.value.collectAsLazyPagingItems()
    val recommendedSeries = homeViewModel.recommendedSeries.value.collectAsLazyPagingItems()
    val trendingMovies = homeViewModel.trendingMovies.value.collectAsLazyPagingItems()
    val trendingSeries = homeViewModel.trendingSeries.value.collectAsLazyPagingItems()
    val popularMovies = homeViewModel.popularMovies.value.collectAsLazyPagingItems()
    val popularSeries = homeViewModel.popularSeries.value.collectAsLazyPagingItems()
    val topRatedMovies = homeViewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val topRatedSeries = homeViewModel.topRatedSeries.value.collectAsLazyPagingItems()
    val nowPlayingMovies = homeViewModel.nowPlayingMovies.value.collectAsLazyPagingItems()
    val upcomingMovies = homeViewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val airingTodaySeries = homeViewModel.airingTodaySeries.value.collectAsLazyPagingItems()

    LaunchedEffect(bookmarkedList.value) {
        bookMarkViewModel.refreshRandomMediaIds() // optional: force a random pick if empty
    }

    LaunchedEffect(bookmarkedList.value) {
        bookMarkViewModel.randomMovieId.collectLatest { movieId ->
            movieId?.let {
                Timber.d("Random movie id: $movieId")
                homeViewModel.getRecommendedMovies(it)
            }
        }
    }
    LaunchedEffect(bookmarkedList.value) {
        bookMarkViewModel.randomTvId.collectLatest { tvId ->
            tvId?.let {
                Timber.d("Random movie id: $tvId")
                homeViewModel.getRecommendedSeries(it)
            }
        }
    }

    val context = LocalContext.current
    BackHandler(enabled = true) {
        (context as Activity).finish()
    }

    Box(
        modifier = modifier.padding(bottom = paddingValues.calculateBottomPadding(), top = 8.dp)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .background(MaterialTheme.colorScheme.surface),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                val genres = if(homeViewModel.selectedOption.value == MOVIE_TAB) homeViewModel.movieGenres.value else homeViewModel.tvGenres.value
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
                            selected = genre.name == homeViewModel.selectedGenre.value,
                            onClick = {
                                if(homeViewModel.selectedGenre.value != genre.name){
                                    homeViewModel.setGenre(genre.name)
                                    homeViewModel.filterByGenre(genre.id, homeViewModel.selectedOption.value)
                                }
                            }
                        )
                    }
                }
            }

            item{
                val trendingMedia = if(homeViewModel.selectedOption.value == MOVIE_TAB) trendingMovies else trendingSeries
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = TRENDING,
                    showShimmer = trendingMedia.itemCount == 0,
                    pagingItems = trendingMedia,
                    modifier = modifier,
                    homeViewModel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    navController = navController,
                    bottomBarNavController = bottomBarNavController,
                )
            }

            item{
                val popularMedia = if(homeViewModel.selectedOption.value == MOVIE_TAB) popularMovies else popularSeries
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = POPULAR,
                    showShimmer = popularMedia.itemCount == 0,
                    pagingItems = popularMedia,
                    modifier = modifier,
                    homeViewModel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    navController = navController,
                    bottomBarNavController = bottomBarNavController,
                )
            }
            item{
                val topRatedMedia = if(homeViewModel.selectedOption.value == MOVIE_TAB) topRatedMovies else topRatedSeries
                ShouldShowMediaHomeScreenSectionOrShimmer(
                    type = TOP_RATED,
                    showShimmer = topRatedMedia.itemCount == 0,
                    pagingItems = topRatedMedia,
                    modifier = modifier,
                    homeViewModel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    navController = navController,
                    bottomBarNavController = bottomBarNavController,
                )
            }
            if(homeViewModel.selectedOption.value == MOVIE_TAB){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = NOW_PLAYING,
                        showShimmer = nowPlayingMovies.itemCount == 0,
                        pagingItems = nowPlayingMovies,
                        modifier = modifier,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        navController = navController,
                        bottomBarNavController = bottomBarNavController,
                    )
                }
            }
            // UI basis lock
            if(homeViewModel.selectedOption.value == MOVIE_TAB){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = UPCOMING,
                        showShimmer = upcomingMovies.itemCount == 0,
                        pagingItems = upcomingMovies,
                        modifier = modifier,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        navController = navController,
                        bottomBarNavController = bottomBarNavController,
                    )
                }
            }
            if(homeViewModel.selectedOption.value == TV_SHOW_TAB){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = AIRING,
                        showShimmer = airingTodaySeries.itemCount == 0,
                        pagingItems = airingTodaySeries,
                        modifier = modifier,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        navController = navController,
                        bottomBarNavController = bottomBarNavController,
                    )
                }
            }
            val recommendedMedia = if(homeViewModel.selectedOption.value == MOVIE_TAB) recommendedMovies else recommendedSeries

            if(recommendedMedia.itemCount != 0){
                item{
                    ShouldShowMediaHomeScreenSectionOrShimmer(
                        type = RECOMMENDED,
                        showShimmer = recommendedMedia.itemCount == 0,
                        pagingItems = recommendedMedia,
                        modifier = modifier,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        navController = navController,
                        bottomBarNavController = bottomBarNavController,
                    )
                }
            }
        }
    }
}