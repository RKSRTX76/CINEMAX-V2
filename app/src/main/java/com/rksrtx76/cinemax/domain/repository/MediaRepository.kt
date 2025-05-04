package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.data.remote.dto.MultiSearchResponseDto
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun getMovieDetails(mediaId : Int) : Resource<MediaDetails>
    suspend fun getTVDetails(mediaId : Int) : Resource<MediaDetails>
    fun getTrendingMovies() : Flow<PagingData<Media>>
    fun getTrendingSeries() : Flow<PagingData<Media>>
    fun getPopularMovies() : Flow<PagingData<Media>>
    fun getPopularSeries() : Flow<PagingData<Media>>
    fun getTopRatedMovies() : Flow<PagingData<Media>>
    fun getTopRatedSeries() : Flow<PagingData<Media>>
    fun getNowPlayingMovies() : Flow<PagingData<Media>>
    fun getAiringTodaySeries() : Flow<PagingData<Media>>
    fun getUpcomingMovies() : Flow<PagingData<Media>>
    fun getSimilarMovies(mediaId : Int) : Flow<PagingData<Media>>
    fun getSimilarSeries(mediaId : Int) : Flow<PagingData<Media>>
    fun getRecommendedMovies(mediaId : Int) : Flow<PagingData<Media>>
    fun getRecommendedSeries(mediaId : Int) : Flow<PagingData<Media>>



}