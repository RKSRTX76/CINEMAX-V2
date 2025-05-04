package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL
import com.rksrtx76.cinemax.util.Resource
import timber.log.Timber

@Composable
fun DetailScreen(
    lifecycleOwner: LifecycleOwner,
    mediaId : Int,
    mediaType : String,
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel<DetailsViewModel>(),
) {
    Timber.d("Detail Screen - passed id - $mediaId, $mediaType")
    LaunchedEffect(mediaId, mediaType) {
        val result = if(mediaType == "movie") {
            detailsViewModel.getMovieDetails(mediaId)
        } else {
            detailsViewModel.getSeriesDetails(mediaId)
        }
    }

    val mediaDetails = if(mediaType == "movie") detailsViewModel.movieDetails.value else detailsViewModel.seriesDetails.value




    // bookmark function implement later

    val surface = MaterialTheme.colorScheme.surface
    var averageColor by remember { mutableStateOf(surface) }

    Timber.d("DetailScreen- url, ${mediaDetails.data?.poster_path}")
    Timber.d("DetailScreen- title, ${mediaDetails.data?.title}")
    Timber.d("DetailScreen- overview, ${mediaDetails.data?.overview}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ){
        when(mediaDetails){
            is Resource.Loading ->{
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is Resource.Error ->{
                Text("Error: ${mediaDetails.statusMessage}", Modifier.align(Alignment.Center))
            }
            is Resource.Success ->{

                var isVideoPlaying by rememberSaveable { mutableStateOf(false) }
                val imageUrl = "${POSTER_IMAGE_BASE_URL}/${mediaDetails.data?.backdrop_path}"

                val imagePainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .size(Size.ORIGINAL)
                        .build()
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp) // check
                            .wrapContentHeight()
                    ){
//                VideoSection(
//                    lifecycleOwner = lifecycleOwner,
//                    navController = navController,
//                    detailsViewModel = detailsViewModel,
//                    media = media,
//                    imageState = imagePainter.state,
//                    onImageLoaded = { color ->
//                        averageColor = color
//                    },
//                    onVideoPlay = { isPlaying ->
//                        isVideoPlaying = isPlaying
//                    }
//                )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            PosterSection(
                                media = mediaDetails,
//                        isVideoPlaying = ,
                                onImageLoaded = {
                                    averageColor = it
                                }
                            )
//
//                    Spacer(modifier = Modifier.width(12.dp))

//                    InfoSection()

//                    Spacer(modifier = Modifier.width(8.dp))

                        }
                    }

//            Spacer(modifier = Modifier.height(16.dp))

//            OverviewSection()

//            Spacer(modifier = Modifier.height(16.dp))

//            CastSection()

//            Spacer(modifier = Modifier.height(16.dp))

//            SimilarMediaSection()

//            Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


