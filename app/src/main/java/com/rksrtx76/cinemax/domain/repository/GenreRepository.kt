package com.rksrtx76.cinemax.domain.repository

import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.util.Resource

interface GenreRepository {
    suspend fun getMovieGenres() : Resource<GenreResponseDto>
    suspend fun getTVGenres() : Resource<GenreResponseDto>
}