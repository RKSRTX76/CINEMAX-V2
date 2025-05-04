package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.VideosDto
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MediaDetailsScreenState(
    val isLoading: Boolean = false,
    val media: MediaDetails? = null,
    val videoId: String ="",
    val readableTime:String = "",

    val similarMediaList: Flow<PagingData<Media>> = emptyFlow(),

    val videoList: Resource<VideosDto> = Resource.Loading(),
//    val moviesGenreList: List<Genre> = emptyList(),
//    val tvGenresList: List<Genre> = emptyList(),

    val castList: Resource<CastResponseDto> = Resource.Loading(),

    )