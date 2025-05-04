package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel
import com.rksrtx76.cinemax.util.Constants.BACKDROP_IMAGE_BASE_URL
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL
import com.rksrtx76.cinemax.util.Resource
import timber.log.Timber

@Composable
fun DetailScreen(
    lifecycleOwner: LifecycleOwner,
    mediaId : Int,
    mediaType : String,
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    homeViewModel: HomeViewModel,
    paddingValues: PaddingValues
) {

    val mediaDetails = if(mediaType == "movie") detailsViewModel.movieDetails.value else detailsViewModel.seriesDetails.value


    // bookmark function implement later


    val surface = MaterialTheme.colorScheme.surface
    var averageColor by remember { mutableStateOf(surface) }


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

                val data = mediaDetails.data
                if(data != null){
                    var isVideoPlaying by rememberSaveable { mutableStateOf(false) }
                    val imageUrl = "${BACKDROP_IMAGE_BASE_URL}/${mediaDetails.data.backdrop_path}"

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
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
//                            .padding(horizontal = 8.dp) // check
                                .wrapContentHeight()
                        ){
                            VideoSection(
                                lifecycleOwner = lifecycleOwner,
                                navController = navController,
                                detailsViewModel = detailsViewModel,
                                media = mediaDetails,
                                type = mediaType,
                                imageState = imagePainter.state,
                                onImageLoaded = { color ->
                                    averageColor = color
                                },
                                onVideoPlay = { isPlaying ->
                                    isVideoPlaying = isPlaying
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                PosterSection(
                                    media = mediaDetails.data,
                                    isVideoPlaying = isVideoPlaying,
                                    onImageLoaded = {
                                        averageColor = it
                                    }
                                )

                                Spacer(modifier = Modifier.width(12.dp))


                                InfoSection(
                                    media = mediaDetails.data,
                                    type = mediaType
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        OverviewSection(
                            media = mediaDetails
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        detailsViewModel.castDetails.value.data?.casts?.let { castList ->
                            CastSection(
                                castList = castList,
                                type = mediaType,
                                navController = navController,
                                title = stringResource(R.string.cast)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        SimilarMediaSection(
                            navController = navController,
                            detailsViewModel = detailsViewModel,
                            homeViewModel = homeViewModel,
                            media = mediaDetails.data,
                            type = mediaType,
                            paddingValues = paddingValues
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}


