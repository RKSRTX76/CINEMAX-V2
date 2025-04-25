package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.util.MediaType
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    fun getTrendingMedia(mediaType : MediaType) : Flow<PagingData<Media>>
    fun getPopularMedia(mediaType : MediaType) : Flow<PagingData<Media>>
    fun getTopRatedMedia(mediaType : MediaType) : Flow<PagingData<Media>>
    fun getNowPlayingMedia(mediaType : MediaType) : Flow<PagingData<Media>>
    fun getAiringToday(mediaType : MediaType) : Flow<PagingData<Media>>
    fun getUpcomingMovies() : Flow<PagingData<Media>>
    fun getSimilarMedia(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Media>>
    fun getRecommendedMedia(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Media>>
}