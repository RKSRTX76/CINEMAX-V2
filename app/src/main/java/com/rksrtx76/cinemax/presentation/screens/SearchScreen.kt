package com.rksrtx76.cinemax.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.presentation.screens.components.FocusedSearchBar
import com.rksrtx76.cinemax.presentation.screens.components.SearchItem
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.SearchViewModel
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.BottomNav
import com.rksrtx76.cinemax.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    selectedItem : MutableState<Int>,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    bottomNavController: NavController,
    paddingValues: PaddingValues,
) {
    val searchQuery = searchViewModel.searchQuery.value
    val searchResults = searchViewModel.searchResult.value.collectAsLazyPagingItems()

    val listState = rememberLazyGridState()

    BackHandler(enabled = true) {
        selectedItem.value = 0
        bottomNavController.navigate(BottomNav.MEDIA_MAIN_SCREEN)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        FocusedSearchBar(
            searchQuery = searchQuery,
            searchViewModel = searchViewModel,
        )

        if(searchQuery.isNotBlank()){
            // check data is ready or not
            when(searchResults.loadState.refresh){
                is LoadState.NotLoading -> {
                    LazyVerticalGrid(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding()),
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        columns = GridCells.Fixed(3)
                    ) {
                        items(searchResults.itemCount){ idx ->
                            val media = searchResults[idx]
                            media?.let {
                                SearchItem(
                                    media = media,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
                is LoadState.Loading ->{
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            strokeWidth = 2.dp
                        )
                    }
                }

                is LoadState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No result found, try another keyword!"
                        )
                    }
                }
            }
        }else{
            val trendingMedia = homeViewModel.trendingMovies.value.collectAsLazyPagingItems()
            LazyColumn {
                items(
                    count = minOf(trendingMedia.itemCount, 5) // Top 5 only
                ) { index ->
                    val media = trendingMedia[index]
                    media?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp, vertical = 6.dp)
                                .clickable {
                                    searchViewModel.updateSearchQuery(media.title)
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = media.title,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                fontFamily = font,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




