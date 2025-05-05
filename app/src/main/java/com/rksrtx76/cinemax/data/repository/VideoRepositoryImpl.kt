package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.model.Video
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.VideosDto
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import com.rksrtx76.cinemax.domain.repository.VideoRepository
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : VideoRepository {

    override suspend fun getVideosForMovies(mediaId: Int): Resource<VideosDto> {
        val response = try{
            mediaApiService.getMovieVideosList(mediaId)
        }catch (e : Exception){
            return Resource.Error("Unexpected Error")
        }
        return Resource.Success(response)
    }

    override suspend fun getVideosForSeries(mediaId: Int): Resource<VideosDto> {
        val response = try{
            mediaApiService.getTvVideosList(mediaId)
        }catch (e : Exception){
            return Resource.Error("Unexpected Error")
        }
        return Resource.Success(response)
    }
}