package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rksrtx76.cinemax.ui.theme.Radius
import com.rksrtx76.cinemax.ui.theme.font

@Composable
fun GenreSelectable(
    genre : String,
    selected : Boolean,
    onClick : () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val animateBackColor by animateColorAsState(
        targetValue =
            if(isDarkTheme){
                if(selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondary
            }else{
                if(selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
            }
        ,
        animationSpec = tween(
            durationMillis = if(selected) 100 else 50,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Radius.dp))
            .background(color = animateBackColor)
            .height(34.dp)
            .widthIn(min = 50.dp)
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    ){
        Text(
            text = genre,
            fontFamily = font,
            fontWeight = if(selected) FontWeight.Normal else FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = if(isDarkTheme){
                if(selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.80f)
            }
            else{
                if(selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
            }
        )
    }
}