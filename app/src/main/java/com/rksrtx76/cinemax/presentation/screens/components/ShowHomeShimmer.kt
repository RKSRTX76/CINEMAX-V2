package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.rksrtx76.cinemax.ui.theme.Radius
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowHomeShimmer(
    title : String,
    modifier : Modifier = Modifier
){
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 22.dp),
            fontWeight = FontWeight.Bold,
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
//            fontFamily = font,
            fontSize = 20.sp
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = modifier
        ) {
            items(15){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(Radius.dp))
                        .shimmerEffect(false)
                )
            }
        }
    }
}