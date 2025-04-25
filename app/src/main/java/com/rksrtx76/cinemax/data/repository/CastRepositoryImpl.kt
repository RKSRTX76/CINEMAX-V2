package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.domain.repository.CastRepository
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : CastRepository {
    /** Non Paging data **/
    override suspend fun getMediaCast(mediaId : Int, mediaType : MediaType) : Resource<CastResponseDto> {
        val response = try{
            if(mediaType == MediaType.MOVIE) mediaApiService.getMovieCast(movieId = mediaId) else mediaApiService.getTvShowCast(showId = mediaId)
        }catch (e : Exception){
            return Resource.Error("Error occurs while Loading cast")
        }
        return Resource.Success(response)
    }
}