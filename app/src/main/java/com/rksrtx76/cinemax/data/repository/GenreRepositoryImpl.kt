package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.domain.repository.GenreRepository
import com.rksrtx76.cinemax.util.Resource
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : GenreRepository {
    override suspend fun getMovieGenres(): Resource<GenreResponseDto> {
        val response = try{
            mediaApiService.getMovieGenres()
        }catch (e: IOException) {
            return Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Error("Server returned an error: ${e.message()}")
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        Timber.d("Movie Genre $response")
        return Resource.Success(response)
    }

    override suspend fun getTVGenres(): Resource<GenreResponseDto> {
        val response = try{
            mediaApiService.getTvShowsGenres()
        }catch (e: IOException) {
            return Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Error("Server returned an error: ${e.message()}")
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        Timber.d("TV Genre $response")
        return Resource.Success(response)
    }

}