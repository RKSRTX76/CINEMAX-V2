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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.screens.details.BookMarkMediaItem
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.ui.theme.font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListScreen(
//    type: String,  // movie or tv
//    mediaItems: LazyPagingItems<Media>,
    navController: NavController,
    bookMarkViewModel: BookMarkViewModel,
    modifier : Modifier = Modifier,
    paddingValues: PaddingValues
) {
    val bookmarkList = bookMarkViewModel.bookMarkList.value.collectAsState(initial = emptyList())
    val currList by remember { mutableStateOf(bookmarkList) }

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
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .background(MaterialTheme.colorScheme.surface)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ){
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

            if(currList.value.isEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 70.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.watchlist),
                        contentDescription = "Bookmarks",
                        tint = Color.Gray.copy(alpha = 0.45f),
                        modifier = Modifier.size(190.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Add movies or shows to your watchlist\nto easily find them later.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontSize = 18.sp,
                        fontFamily = font
                    )
                }
            }
            else{
                val listState = rememberLazyGridState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyVerticalGrid(
                        state = listState,
//                        .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = padding.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding()),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        columns = GridCells.Fixed(3)
                    ) {
                        items(currList.value.size){ idx->
                            currList.value[idx]?.let {
                                BookMarkMediaItem(
                                    media = currList.value[idx],
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