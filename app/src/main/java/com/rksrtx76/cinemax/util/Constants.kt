package com.rksrtx76.cinemax.util

import com.rksrtx76.cinemax.BuildConfig

object Constants{
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val BACKDROP_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780/"
    const val API_KEY = BuildConfig.API_KEY
    const val STARTING_PAGE = 0


    const val POPULAR = "popular"
    const val TOP_RATED = "top_rated"
    const val NOW_PLAYING = "now_playing"
    const val TRENDING_TIME = "week"
    const val ALL = "all"
    const val MOVIE = "movie"
    const val TV = "tv"
    const val TRENDING = "trending"
    const val UPCOMING = "upcoming"
    const val AIRING = "airing_today"
    const val RECOMMENDED = "recommended"


    const val MOVIE_TAB = "movie"
    const val TV_SHOW_TAB = "tv"


}