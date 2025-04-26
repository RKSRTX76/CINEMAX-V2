package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Screen

@Composable
fun MediaHomeScreenSection(
    title : String,
    type : String,
    mediaItems: LazyPagingItems<Media>,
    modifier : Modifier = Modifier,
    navController: NavController,
    bottomNavController: NavHostController
) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = font,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .alpha(0.7f)
                    .clickable {
//                        navigate to mediaListScreen (Note-> title is category for mediaListScreen)
                        // route -> Movie/category(title for mediaListScreen)
                        navController.navigate(
                            "${Screen.MEDIA_LIST_SCREEN}/${type}/${title}"
                        )
                    },
                text = stringResource(R.string.see_all),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = font,
                fontSize = 14.sp
            )
        }

        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            // media list is a StateFlow<Flow<PagingData<Media>>> so .size will not work
            // take only first 25 items
            items(count = minOf(mediaItems.itemCount,25)){ idx->
                mediaItems[idx]?.let { media ->
                    Item(
                        media = media,
                        navController = navController,
                        modifier = Modifier
                            .height(195.dp)
                            .width(125.dp)
                    )
                }
            }
        }
    }
}