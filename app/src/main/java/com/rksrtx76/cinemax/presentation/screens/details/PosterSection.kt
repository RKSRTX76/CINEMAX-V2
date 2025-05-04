package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.EventListener
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.ui.theme.SmallRadius
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL
import com.rksrtx76.cinemax.util.Resource
import timber.log.Timber

@Composable
fun PosterSection(
    media : MediaDetails?,
    isVideoPlaying : Boolean,
    onImageLoaded : (color : Color) -> Unit
){
    val posterUrl = "${POSTER_IMAGE_BASE_URL}${media?.poster_path}"
    val posterPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .placeholder(R.drawable.cinemax) // add a drawable in your res
            .error(R.drawable.cinemax)
            .size(Size.ORIGINAL)
            .build()
    )
    val posterState = posterPainter.state

    Timber.d("Poster Section- url, $posterUrl")
    Timber.d("Poster Section- title, ${media?.title}")
    Timber.d("Poster Section- overview, ${media?.overview}")
    Timber.d("Poster Section poster state, $posterState")

    val posterWidth by animateDpAsState(targetValue = if (isVideoPlaying) 145.dp else 170.dp, animationSpec = tween(durationMillis = 300))
    val posterHeight by animateDpAsState(targetValue = if (isVideoPlaying) 200.dp else 245.dp, animationSpec = tween(durationMillis = 300))
    val spacerHeight by animateDpAsState(targetValue = if (isVideoPlaying) 245.dp else 190.dp, animationSpec = tween(durationMillis = 300))
    val cardElevation by animateFloatAsState(targetValue = if (isVideoPlaying) 8f else 5f, animationSpec = tween(durationMillis = 300))

    Column {
        Spacer(Modifier.height(spacerHeight))
        Card(
            modifier = Modifier
                .width(posterWidth)
                .height(posterHeight)
                .padding(start = 16.dp),
            shape = RoundedCornerShape(SmallRadius.dp),
            elevation = CardDefaults.cardElevation(cardElevation.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                MovieImage(
                    imageState = posterState,
                    description = media?.title ?: "",
                    noImageId = painterResource(id = R.drawable.cinemax)
                ) { color ->
                    onImageLoaded(color)
                }
            }
        }
    }

}