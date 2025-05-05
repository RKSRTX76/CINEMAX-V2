package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.model.Video
import com.rksrtx76.cinemax.data.remote.dto.VideosDto
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideosForMovies(mediaId : Int) : Resource<VideosDto>
    suspend fun getVideosForSeries(mediaId : Int) : Resource<VideosDto>
}