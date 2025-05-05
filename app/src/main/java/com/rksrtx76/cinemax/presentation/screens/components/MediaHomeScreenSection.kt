package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.font
import timber.log.Timber

@Composable
fun MediaHomeScreenSection(
    title : String,
    mediaItems: LazyPagingItems<Media>,
    modifier : Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel,
    detailsViewModel : DetailsViewModel,
    bottomNavController: NavController
) {


    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = font,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Timber.d("MediaHomeScreenSection: ${homeViewModel.selectedOption.value}")
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            // media list is a StateFlow<Flow<PagingData<Media>>> so .size will not work
            // take only first 25 items
            items(count = minOf(mediaItems.itemCount,250)){ idx->
                mediaItems[idx]?.let { media ->
                    Item(
                        media = media,
                        type = homeViewModel.selectedOption.value,
                        navController = navController,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        modifier = Modifier
                            .height(195.dp)
                            .width(125.dp)
                    )
                }
            }
        }
    }
}