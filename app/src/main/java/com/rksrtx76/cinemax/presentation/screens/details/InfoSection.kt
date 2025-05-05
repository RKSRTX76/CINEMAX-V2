package com.rksrtx76.cinemax.presentation.screens.details

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.rksrtx76.cinemax.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.cinemax.data.local.BookMark
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.presentation.viewmodel.BookMarkViewModel
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Constants.POSTER_IMAGE_BASE_URL

@Composable
fun InfoSection(
    media : MediaDetails,
    type : String,
    mediaId : Int,
    bookMarkViewModel: BookMarkViewModel
){
    val genres = GenresProvider(genres = media.genres)

//    val addToBookmark = bookMarkViewModel.insertToBookmark.value
    val addToBookmark = bookMarkViewModel.insertToBookmark.collectAsState()



    Column {
        Spacer(Modifier.height(240.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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


            val context = LocalContext.current
            IconButton(
                modifier = Modifier.padding(end = 30.dp),
                onClick = {
                    if(addToBookmark.value != 0){
                        bookMarkViewModel.removeABookmark(mediaId)
                        Toast.makeText(context, "Removed from bookmark", Toast.LENGTH_SHORT).show()
                    }else{
                        bookMarkViewModel.addToBookmarkList(
                            BookMark(
                                mediaId = mediaId,
                                imagePath = "${POSTER_IMAGE_BASE_URL}${media.poster_path}",
                                title = media.title ?: "",
                                mediaType = type,
                                releaseDate = media.release_date.toString(),
                                description = media.overview ?: "",
                                rating = media.vote_average.toString().take(3)
                            )
                        )
                        Toast.makeText(context, "Added to bookmark", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if(addToBookmark.value != 0) R.drawable.ic_added_to_list else R.drawable.ic_add_to_list
                    ),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "Bookmark"
                )
            }
        }
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
                .padding(horizontal = 5.dp, vertical = 0.dp),
            text = ageRating,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = font,
            fontSize = 14.sp
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

    }
}