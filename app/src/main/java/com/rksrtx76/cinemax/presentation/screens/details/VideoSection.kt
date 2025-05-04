package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.util.Resource

@Composable
fun VideoSection(
    lifecycleOwner : LifecycleOwner,
    detailsViewModel: DetailsViewModel,
    media  : Resource<MediaDetails>,
    type : String,
    imageState : AsyncImagePainter.State,
    onImageLoaded : (color : Color) -> Unit,
    onVideoPlay : (Boolean) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    if(type =="movie") detailsViewModel.getMovieVideos(media.data?.id!!) else detailsViewModel.getSeriesVideos(media.data?.id!!)

    val videosList = if (type == "movie") detailsViewModel.movieVideos.value else detailsViewModel.seriesVideos.value
    val videoId = videosList.data?.results?.randomOrNull()?.key

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable {
                if(videoId != null){
                    isPlaying = true
                    onVideoPlay(isPlaying)
                }
            },
        contentAlignment = Alignment.Center
    ){
        if(isPlaying){
            WatchVideoScreen(
                lifecycleOwner = lifecycleOwner,
                videoId = videoId,
                onVideoPlay = { playing ->
                    onVideoPlay(playing)
                    isPlaying = playing
                }
            )
        }else{
            MediaImage(
                imageState = imageState,
                description = media.data.title,// mediaDetailsUiState.mediaDetails!!.title,
                noImageId = null
            ){ color ->
                onImageLoaded(color)
            }
            // Back button
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .size(35.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Bookmark button



            // Play button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(45.dp)
                    .alpha(0.65f)
                    .background(Color.Black.copy(alpha = 0.65f))
            ){
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = stringResource(R.string.watch_trailer),
                    tint = Color.Black,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}