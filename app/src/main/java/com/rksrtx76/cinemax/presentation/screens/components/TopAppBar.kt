package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.MediaType

@Composable
fun TopAppBar(
    homeViewModel: HomeViewModel,
    tabPage: MediaType,
    onTabSelected : (MediaType) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
//                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
//                .padding(vertical = 12.dp)
    ){
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(R.drawable.cinemax),
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        val selectedMediaType by rememberUpdatedState(
            homeViewModel.selectedMediaType.value
        )

        TabRow(
            selectedTabIndex = tabPage.ordinal,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            indicator = { tabPositions->
            }
        ) {
            HomeTab(
                title = stringResource(R.string.movies),
                fontWeight = if(selectedMediaType == MediaType.MOVIE) FontWeight.Bold else FontWeight.Normal,
                onClick = {
                    onTabSelected(MediaType.MOVIE)
                }
            )
            HomeTab(
                title = stringResource(R.string.tv_series),
                fontWeight = if(selectedMediaType == MediaType.TVSHOW) FontWeight.Bold else FontWeight.Normal,
                onClick = {
                    onTabSelected(MediaType.TVSHOW)
                }
            )
        }
    }
}

@Composable
fun HomeTab(
    title : String,
    fontWeight: FontWeight,
    onClick : ()->Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = fontWeight,
            fontFamily = font,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}