package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.util.Constants

@Composable
fun getTitle(type : String) : String = when(type){
        Constants.TRENDING ->{
            stringResource(R.string.trending)
        }
        Constants.POPULAR ->{
            stringResource(R.string.popular)
        }
        Constants.TOP_RATED ->{
            stringResource(R.string.top_rated)
        }
        Constants.NOW_PLAYING ->{
            stringResource(R.string.now_playing)
        }
        Constants.UPCOMING ->{
            stringResource(R.string.upcoming_movies)
        }
        Constants.AIRING ->{
            stringResource(R.string.airing_today)
        }
        Constants.RECOMMENDED ->{
            stringResource(R.string.recommended)
        }
        else -> {
            ""
        }
    }



