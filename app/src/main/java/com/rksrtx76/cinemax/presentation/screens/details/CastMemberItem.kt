package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rksrtx76.CINEMAX.model.CastDetails
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL

@Composable
fun CastMemberItem(
    cast: CastDetails,
    modifier: Modifier = Modifier
) {
    val imageUrl = "$POSTER_IMAGE_BASE_URL${cast.profilePath}"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(120.dp)
    ) {
        val imagePainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .size(Size.ORIGINAL)
                .build()
        )
        val imageState = imagePainter.state
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            when (imageState) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center),
                        strokeWidth = 2.dp
                    )
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    Icon(
                        painter = painterResource(R.drawable.cinemax),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                else -> {
                    // Handle other states if necessary
                }
            }
        }
        Text(
            text = cast.name,
            fontFamily = font,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = cast.character,
            fontFamily = font,
            fontStyle = FontStyle.Italic,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}