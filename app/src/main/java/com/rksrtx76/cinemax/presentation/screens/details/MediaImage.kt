package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import com.rksrtx76.cinemax.presentation.screens.components.getAverageColor
import com.rksrtx76.cinemax.presentation.screens.components.shimmerEffect
import com.rksrtx76.cinemax.ui.theme.Radius

@Composable
fun MediaImage(
    imageState : AsyncImagePainter.State,
    description : String?,
    noImageId : Painter?,
    onImageFinished : (dominantColor : Color) ->  Unit
) {
    when(imageState){
        is AsyncImagePainter.State.Error -> {
            if(noImageId != null){
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(Radius.dp))
                        .alpha(0.6f),
                    painter = noImageId,
                    contentDescription = description,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect(false)
            )
        }
        is AsyncImagePainter.State.Success -> {
            val imageBitmap = imageState.result.drawable.toBitmap()
            onImageFinished(getAverageColor(imageBitmap = imageBitmap.asImageBitmap()))
            Image(
                bitmap = imageBitmap.asImageBitmap(),
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
        else -> { }
    }
}