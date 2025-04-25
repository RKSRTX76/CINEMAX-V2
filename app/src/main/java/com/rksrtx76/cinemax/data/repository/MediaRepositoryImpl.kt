package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.paging.AiringToday
import com.rksrtx76.cinemax.paging.NowPlayingMediaSource
import com.rksrtx76.cinemax.paging.PopularMediaSource
import com.rksrtx76.cinemax.paging.RecommendedMediaSource
import com.rksrtx76.cinemax.paging.SimilarMediaSource
import com.rksrtx76.cinemax.paging.TopRatedMediaSource
import com.rksrtx76.cinemax.paging.TrendingMediaSource
import com.rksrtx76.cinemax.paging.UpcomingMovieSource
import com.rksrtx76.cinemax.util.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : MediaRepository {
    override fun getTrendingMedia(mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingMediaSource(api = mediaApiService, mediaType = mediaType)
            }
        ).flow
    }
    override fun getPopularMedia(mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularMediaSource(api = mediaApiService, mediaType = mediaType)
            }
        ).flow
    }
    override fun getTopRatedMedia(mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedMediaSource(api = mediaApiService, mediaType = mediaType)
            }
        ).flow
    }
    override fun getNowPlayingMedia(mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                NowPlayingMediaSource(api = mediaApiService, mediaType = mediaType)
            }
        ).flow
    }
    override fun getAiringToday(mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                AiringToday(api = mediaApiService, mediaType = mediaType)
            }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                UpcomingMovieSource(api = mediaApiService)
            }
        ).flow
    }

    override fun getSimilarMedia(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SimilarMediaSource(api = mediaApiService, mediaId = mediaId ,mediaType = mediaType)
            }
        ).flow
    }
    override fun getRecommendedMedia(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Media>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                RecommendedMediaSource(api = mediaApiService, mediaId = mediaId ,mediaType = mediaType)
            }
        ).flow
    }
}