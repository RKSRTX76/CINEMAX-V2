package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.font

@Composable
fun TabScreen(
    homeViewModel: HomeViewModel,
    tabPage: Int,
    onTabSelected: (Int) -> Unit,
) {


    TabRow(
        selectedTabIndex = tabPage,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabPage]),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    ) {
        HomeTab(
            title = stringResource(R.string.movies),
            fontWeight = if(tabPage == 0) FontWeight.Bold else FontWeight.Normal,
            onClick = {
                onTabSelected(0)
            }
        )
        HomeTab(
            title = stringResource(R.string.tv_series),
            fontWeight = if(tabPage == 1) FontWeight.Bold else FontWeight.Normal,
            onClick = {
                onTabSelected(1)
            }
        )
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
            .padding(8.dp),
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