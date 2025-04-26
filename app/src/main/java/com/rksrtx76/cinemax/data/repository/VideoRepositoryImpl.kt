package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.model.Video
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import com.rksrtx76.cinemax.domain.repository.VideoRepository
import com.rksrtx76.cinemax.paging.SearchMediaSource
import com.rksrtx76.cinemax.paging.SimilarMediaSource
import com.rksrtx76.cinemax.paging.VideoMediaSource
import com.rksrtx76.cinemax.util.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : VideoRepository {
    override fun getVideos(mediaId: Int, mediaType: MediaType): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                VideoMediaSource(api = mediaApiService, mediaId = mediaId ,mediaType = mediaType)
            }
        ).flow
    }
}