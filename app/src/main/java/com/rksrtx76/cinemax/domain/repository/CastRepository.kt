package com.rksrtx76.cinemax.domain.repository

import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource

interface CastRepository {
    suspend fun getMovieCastDetails(mediaId : Int) : Resource<CastResponseDto>
    suspend fun getSeriesCastDetails(mediaId : Int) : Resource<CastResponseDto>
}