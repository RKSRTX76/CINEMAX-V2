package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL
import com.rksrtx76.cinemax.util.Screen

@Composable
fun MediaItem(
    media: Media,
    category: String,
    navController: NavController
) {
    val imageUrl = "$POSTER_IMAGE_BASE_URL/${media.posterPath}"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .size(Size.ORIGINAL)
            .build()
    )

    val imageState = painter.state

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
//                navController.navigate(
//                    "${Screen.DETAILS_SCREEN}?id=${media.id}&type=${media.mediaType}&category=$category"
//                )
            }
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        when (imageState) {
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = media.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is AsyncImagePainter.State.Error -> {
                Icon(
                    painter = painterResource(id = R.drawable.cinemax),
                    contentDescription = media.title,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .alpha(0.5f)
                )
            }
            else -> {}
        }

        // Rating badge
        val vote = media.voteAverage.toString()
        val firstDigit = vote.first().digitToIntOrNull() ?: 0

        val starColor = when(firstDigit){
            in 0..4 -> Color(0xFFA52A2A)
            in 5..7 -> Color(0xFF4CAF50)
            else -> Color(0xFFFFEB3B)
        }
        if(!vote.startsWith("0")){
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 2.dp)
                    .padding(end = 6.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = starColor,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = vote.take(3),
                    fontFamily = font,
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}