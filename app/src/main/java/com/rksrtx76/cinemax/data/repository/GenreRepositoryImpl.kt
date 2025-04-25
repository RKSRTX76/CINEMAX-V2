package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.domain.repository.GenreRepository
import com.rksrtx76.cinemax.util.Resource
import com.rksrtx76.cinemax.util.MediaType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : GenreRepository {
    override suspend fun getMediaGenre(mediaType : MediaType) : Resource<GenreResponseDto>{
        val response = try{
            if(mediaType == MediaType.MOVIE) mediaApiService.getMovieGenres() else mediaApiService.getTvShowGenres()
        }catch (e : Exception){
            return Resource.Error("Error occurs while Loading Genre")
        }
        return Resource.Success(response)
    }
}