package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.local.BookMark
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val bookMarkRepository: BookMarkListRepository,
    private val mediaRepository: MediaRepository,
    private val castRepository: CastRepository,
    private val genreRepository: GenreRepository,
    private val searchRepository: SearchRepository
) {
    fun getBookMarkList() : Flow<List<BookMark>> {
        return bookMarkRepository.getBookMarkList()
    }
    fun getBookMark(mediaId : Int) : BookMark {
        return bookMarkRepository.getBookMark(mediaId)
    }
    suspend fun insertToBookMarkList(media : BookMark){
        bookMarkRepository.insertToBookMarkList(media)
    }
    suspend fun exists(mediaId : Int) : Int{
        return bookMarkRepository.exists(mediaId)
    }
    suspend fun removeFromBookMarkList(mediaId : Int){
        bookMarkRepository.removeFromBookMarkList(mediaId)
    }
    suspend fun deleteFromBookMarkList(){
        bookMarkRepository.deleteFromBookMarkList()
    }

    suspend fun getMovieDetails(mediaId : Int) : Resource<MediaDetails> {
        return mediaRepository.getMovieDetails(mediaId)
    }
    suspend fun getTVDetails(mediaId : Int) : Resource<MediaDetails> {
        return mediaRepository.getTVDetails(mediaId)
    }
    fun getTrendingMovies() : Flow<PagingData<Media>> {
        return mediaRepository.getTrendingMovies()
    }
    fun getTrendingSeries() : Flow<PagingData<Media>> {
        return mediaRepository.getTrendingSeries()
    }
    fun getPopularMovies() : Flow<PagingData<Media>> {
        return mediaRepository.getPopularMovies()
    }
    fun getPopularSeries() : Flow<PagingData<Media>> {
        return mediaRepository.getPopularSeries()
    }
    fun getTopRatedMovies() : Flow<PagingData<Media>> {
        return mediaRepository.getTopRatedMovies()
    }
    fun getTopRatedSeries() : Flow<PagingData<Media>> {
        return mediaRepository.getTopRatedSeries()
    }
    fun getNowPlayingMovies() : Flow<PagingData<Media>> {
        return mediaRepository.getNowPlayingMovies()
    }
    fun getAiringTodaySeries() : Flow<PagingData<Media>> {
        return mediaRepository.getAiringTodaySeries()
    }
    fun getUpcomingMovies() : Flow<PagingData<Media>> {
        return mediaRepository.getUpcomingMovies()
    }
    fun getSimilarMovies(mediaId : Int) : Flow<PagingData<Media>> {
        return mediaRepository.getSimilarMovies(mediaId)
    }
    fun getSimilarSeries(mediaId : Int) : Flow<PagingData<Media>> {
        return mediaRepository.getSimilarSeries(mediaId)
    }
    fun getRecommendedMovies(mediaId : Int) : Flow<PagingData<Media>> {
        return mediaRepository.getRecommendedMovies(mediaId)
    }
    fun getRecommendedSeries(mediaId : Int) : Flow<PagingData<Media>> {
        return mediaRepository.getRecommendedSeries(mediaId)
    }
    suspend fun getMovieCastDetails(mediaId : Int) : Resource<CastResponseDto> {
        return castRepository.getMovieCastDetails(mediaId)
    }
    suspend fun getSeriesCastDetails(mediaId : Int) : Resource<CastResponseDto> {
        return castRepository.getSeriesCastDetails(mediaId)
    }

    suspend fun getMovieGenres() : Resource<GenreResponseDto> {
        return genreRepository.getMovieGenres()
    }
    suspend fun getTVGenres() : Resource<GenreResponseDto> {
        return genreRepository.getTVGenres()
    }

    fun multiSearch(query : String) : Flow<PagingData<Search>> {
        return searchRepository.multiSearch(query)
    }
}