package com.rksrtx76.cinemax.presentation.screens

import android.app.Activity
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.screens.components.GenreSelectable
import com.rksrtx76.cinemax.presentation.screens.components.ShouldShowMediaHomeScreenSectionOrShimmer
import com.rksrtx76.cinemax.presentation.screens.components.TabScreen
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants.NOW_PLAYING
import com.rksrtx76.cinemax.util.Constants.popularScreen
import com.rksrtx76.cinemax.util.Constants.recommendedListScreen
import com.rksrtx76.cinemax.util.Constants.topRatedAllListScreen
import com.rksrtx76.cinemax.util.Constants.trendingAllListScreen
import com.rksrtx76.cinemax.util.Constants.upcomingMoviesScreen
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    paddingValues : PaddingValues,
    bottomBarNavController : NavHostController,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var tabPage by remember { mutableStateOf(MediaType.MOVIE) }

    // Default TopAppBar with title
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        TopAppBar(
            title = {},
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.cinemax),
                    tint = Color.Unspecified,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background, // or whatever fixed color you want
                scrolledContainerColor = MaterialTheme.colorScheme.background // fix scrolled color too
            )
        )
        TabScreen(
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
        )
        // Trigger Home Navigation Controller
        HomeNavigation(
            modifier = modifier,
            paddingValues = paddingValues,
            bottomBarNavController = bottomBarNavController,
            scrollBehavior = scrollBehavior,
            homeViewModel = homeViewModel,
            bookMarkViewModel = bookMarkViewModel
        )
    }
}
