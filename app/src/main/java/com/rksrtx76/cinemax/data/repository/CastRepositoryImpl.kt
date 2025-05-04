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

    override suspend fun getMovieCastDetails(mediaId: Int): Resource<CastResponseDto> {
        val response = try {
            mediaApiService.getMovieCredits(mediaId)
        }catch (e : Exception){
            return Resource.Error("Unexpected Error")
        }
        return Resource.Success(response)
    }

    override suspend fun getSeriesCastDetails(mediaId: Int): Resource<CastResponseDto> {
        val response = try {
            mediaApiService.getTvCredits(mediaId)
        }catch (e : Exception){
            return Resource.Error("Unexpected Error")
        }
        return Resource.Success(response)
    }
}