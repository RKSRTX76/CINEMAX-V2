package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.ui.theme.font

@Composable
fun InfoSection(
    media : MediaDetails,
    type : String
){
    val genres = GenresProvider(genres = media.genres)

        Column {
            Spacer(Modifier.height(242.dp))

            Text(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 5.dp, vertical = 0.dp),
                text = if(type == "movie") "Movie" else "Series",
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = font,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = media.title ?: "",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontFamily = font,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Release date: " + media.release_date.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontFamily = font,
                fontSize = 13.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    modifier = Modifier,
                    starsModifier = Modifier.size(18.dp),
                    rating = media.vote_average?.div(2)!!
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    text = media.vote_average.toString().take(3),
                    fontFamily = font,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            val ageRating = if (media.adult == true) "18+" else "12+"
            Text(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 4.dp, vertical = 0.dp),
                text = ageRating,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = font,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier= Modifier.padding(end = 8.dp),
                text = genres,
                fontFamily = font,
                fontSize = 13.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(6.dp))

//            Text(
//                modifier= Modifier.padding(end = 8.dp),
//                text = media.runtime.toString(),
//                fontFamily = font,
//                fontSize = 15.sp,
//                color = MaterialTheme.colorScheme.onSurface,
//                lineHeight = 16.sp
//            )

        }
}