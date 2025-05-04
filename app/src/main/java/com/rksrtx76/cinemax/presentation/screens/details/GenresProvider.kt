package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.runtime.Composable
import com.rksrtx76.CINEMAX.model.Genre

@Composable
fun GenresProvider(
    genres : List<Genre>?
) : String {
    var genreList = ""
    for(genre in genres!!){
        genreList += genre.name + "-"
    }

    return genreList.dropLastWhile {
        it ==' ' || it == ',' || it == '-'
    }

}
