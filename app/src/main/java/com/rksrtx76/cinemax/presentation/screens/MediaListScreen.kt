package com.rksrtx76.cinemax.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.screens.components.ListShimmerEffect
import com.rksrtx76.cinemax.presentation.screens.components.MediaItem
import com.rksrtx76.cinemax.ui.theme.BigRadius

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListScreen(
    type: String,  // movie or tv
    category : String,   // popular, top_rated, trending, etc   (title for the screen)
    mediaItems: LazyPagingItems<Media>,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,  // helps go go back to the previous screen
    modifier : Modifier = Modifier
) {
    BackHandler(
        enabled = true
    ) {
        // go back to prev screen
        navController.popBackStack()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {},
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cinemax),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp),
                     tint = Color.Unspecified,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background, // or whatever fixed color you want
                    scrolledContainerColor = MaterialTheme.colorScheme.background // fix scrolled color too
                )
            )

        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(top = paddingValues.calculateTopPadding())
                .background(MaterialTheme.colorScheme.surface)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ){
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
//            Offline
            if(mediaItems.itemCount == 0){
                ListShimmerEffect(
                    title = category,
                    radius = BigRadius
                )
            }
            else{
                val listState = rememberLazyGridState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyVerticalGrid(
                        state = listState,
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        columns = GridCells.Fixed(3)
                    ) {
                        items(mediaItems.itemCount){ idx->
                            mediaItems[idx]?.let {
                                MediaItem(
                                    media = mediaItems[idx]!!,
                                    category = category,
                                    navController = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}