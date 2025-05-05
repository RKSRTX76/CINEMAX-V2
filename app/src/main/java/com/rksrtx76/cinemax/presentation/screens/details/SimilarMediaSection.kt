package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.presentation.screens.components.Item
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Resource
import com.rksrtx76.cinemax.util.Screen
import timber.log.Timber
import kotlin.collections.get

@Composable
fun SimilarMediaSection(
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    homeViewModel: HomeViewModel,
    media: MediaDetails,
    type : String,
    paddingValues: PaddingValues
){
    val title = stringResource(R.string.similar)
    val mediaList = if(type == "movie") homeViewModel.similarMovies else homeViewModel.similarSeries
    val mediaItems = mediaList.value.collectAsLazyPagingItems()

    if(mediaItems.itemCount != 0){
        Column(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = font,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier
                        .alpha(0.85f)
                        .clickable {
//                            navController.navigate(
//                                "${Screen.SIMILAR_MEDIA_SCREEN}?title=${media.title}"
//                            )
                        },
                    text = stringResource(R.string.see_all),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = font,
                    fontSize = 14.sp
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(mediaItems.itemCount){ idx->
                    mediaItems[idx]?.let { media->
                        Timber.d("Similar Media id: ${media.id}")
                        Timber.d("Similar Media type: ${media.title}")
                        Item(
                        media = media,
                        type = type,
                        navController = navController,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel,
                        modifier = Modifier
                            .height(210.dp)
                            .width(135.dp)
                        )
                    }
                }
            }
        }
    }
}