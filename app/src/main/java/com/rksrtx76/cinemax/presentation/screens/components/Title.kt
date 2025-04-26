package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.util.Constants

@Composable
fun getTitle(type : String) : String = when(type){
        Constants.trendingAllListScreen ->{
            stringResource(R.string.trending)
        }
        Constants.popularScreen ->{
            stringResource(R.string.popular)
        }
        Constants.topRatedAllListScreen ->{
            stringResource(R.string.top_rated)
        }
        Constants.NOW_PLAYING ->{
            stringResource(R.string.now_playing)
        }
        Constants.upcomingMoviesScreen ->{
            stringResource(R.string.upcoming_movies)
        }
        Constants.airingTodayTvSeriesScreen ->{
            stringResource(R.string.airing_today)
        }
        Constants.recommendedListScreen ->{
            stringResource(R.string.recommended)
        }
        else -> {
            ""
        }
    }



