package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.presentation.viewmodel.DetailsViewModel
import com.rksrtx76.cinemax.presentation.viewmodel.HomeViewModel

//@Composable
//fun VideoSection(
//    lifecycleOwner : LifecycleOwner,
//    detailsViewModel: DetailsViewModel,
//    media  : Media,
//    imageState : AsyncImagePainter.State,
//    onImageLoaded : (color : Color) -> Unit,
//    onVideoPlay : (Boolean) -> Unit,
//    navController: NavController,
//) {
//    val context = LocalContext.current
//    var isPlaying by remember { mutableStateOf(false) }
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(220.dp)
//            .clickable {
//                // Todo
//            },
//        contentAlignment = Alignment.Center
//    ){
//        if(isPlaying){
////            val video = videoDetailsUiState.videoDetails?.results?.randomOrNull()
////            val videoId = video?.key
////            WatchVideoScreen(
////                lifecycleOwner = lifecycleOwner,
////                videoId = videoId,
////                onVideoPlay = { playing ->
////                    onVideoPlay(playing)
////                    isPlaying = playing
////                }
////            )
//        }else{
//            MediaImage(
//                imageState = imageState,
//                description = mediaDetailsUiState.mediaDetails!!.title,
//                noImageId = null
//            ){ color ->
//                onImageLoaded(color)
//            }
//            // Back button
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(8.dp)
//                    .clip(RoundedCornerShape(50.dp))
//                    .size(35.dp)
//                    .background(Color.Black.copy(alpha = 0.5f))
//                    .clickable {
//                        navController.popBackStack()
//                    },
//                contentAlignment = Alignment.Center
//            ){
//                Icon(
//                    imageVector = Icons.Rounded.ArrowBackIos,
//                    contentDescription = null,
//                    tint = Color.Black,
//                    modifier = Modifier.size(22.dp)
//                )
//            }
//
//            // Play button
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(50.dp))
//                    .size(45.dp)
//                    .alpha(0.65f)
//                    .background(Color.Black.copy(alpha = 0.65f))
//            )
//        }
//    }
//}