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
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


//@Singleton
//class VideoRepositoryImpl @Inject constructor(
//    private val mediaApiService: MediaApiService
//) : VideoRepository {
//
//    override suspend fun getVideos(mediaId: Int, mediaType: MediaType): Resource<VideosDto> {
//        val response = try {
//            if(mediaType == MediaType.MOVIE) mediaApiService.getVideosList(mediaId = mediaId, type = "movie") else mediaApiService.getVideosList(mediaId = mediaId, type = "movie")
//        }catch (e : Exception){
//            return Resource.Error("Error occurs while Loading Videos")
//        }
//        return if(response != null){
//            Resource.Success(response)
//        }else{
//            Resource.Error("Error occurs while Loading Videos because of null value")
//        }
//    }
//}