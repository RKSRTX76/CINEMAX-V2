package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.rksrtx76.CINEMAX.model.Genre
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.domain.repository.GenreRepository
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.util.Constants.MOVIE_TAB
import com.rksrtx76.cinemax.util.Constants.TV_SHOW_TAB
import com.rksrtx76.cinemax.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val _selectedOption = mutableStateOf(MOVIE_TAB)
    val selectedOption : State<String> = _selectedOption

    private val _selectedGenre = mutableStateOf("")
    val selectedGenre : State<String> = _selectedGenre

    private val _getTVGenres = mutableStateOf<List<Genre>>(emptyList())
    val tvGenres: State<List<Genre>> = _getTVGenres

    private val _getMovieGenres = mutableStateOf<List<Genre>>(emptyList())
    val movieGenres: State<List<Genre>> = _getMovieGenres

    private val _getPopularMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val popularMovies : State<Flow<PagingData<Media>>> = _getPopularMovies

    private val _getPopularSeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val popularSeries : State<Flow<PagingData<Media>>> = _getPopularSeries

    private val _getTrendingMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val trendingMovies : State<Flow<PagingData<Media>>> = _getTrendingMovies

    private val _getTrendingSeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val trendingSeries : State<Flow<PagingData<Media>>> = _getTrendingSeries

    private val _getTopRatedMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val topRatedMovies : State<Flow<PagingData<Media>>> = _getTopRatedMovies

    private val _getTopRatedSeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val topRatedSeries : State<Flow<PagingData<Media>>> = _getTopRatedSeries

    private val _getNowPlayingMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val nowPlayingMovies : State<Flow<PagingData<Media>>> = _getNowPlayingMovies

    private val _getAiringTodaySeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val airingTodaySeries : State<Flow<PagingData<Media>>> = _getAiringTodaySeries

    private val _getUpcomingMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val upcomingMovies : State<Flow<PagingData<Media>>> = _getUpcomingMovies

    private val _getSimilarMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val similarMovies : State<Flow<PagingData<Media>>> = _getSimilarMovies

    private val _getSimilarSeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val similarSeries : State<Flow<PagingData<Media>>> = _getSimilarSeries

    private val _getRecommendedMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val recommendedMovies : State<Flow<PagingData<Media>>> = _getRecommendedMovies

    private val _getRecommendedSeries = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val recommendedSeries : State<Flow<PagingData<Media>>> = _getRecommendedSeries

    init {
        getMovieGenres()
        getTVGenres()
        getPopularMovies(null)
        getPopularSeries(null)
        getTrendingMovies(null)
        getTrendingSeries(null)
        getTopRatedMovies(null)
        getTopRatedSeries(null)
        getNowPlayingMovies(null)
        getAiringTodaySeries(null)
        getUpcomingMovies(null)
        // do not initialise these
//        getSimilarMovies(null)
//        getSimilarSeries(null)
//        getRecommendedMovies(null)
//        getRecommendedSeries(null)
    }

    fun getPopularMovies(genreId : Int? ) {
        viewModelScope.launch {
            _getPopularMovies.value = if(genreId != null){
                mediaRepository.getPopularMovies().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getPopularMovies().cachedIn(viewModelScope)
            }
        }
    }
    fun getPopularSeries(genreId : Int? ) {
        viewModelScope.launch {
            _getPopularSeries.value = if(genreId != null){
                mediaRepository.getPopularSeries().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getPopularSeries().cachedIn(viewModelScope)
            }
        }
    }
    fun getTrendingMovies(genreId : Int? ) {
        viewModelScope.launch {
            _getTrendingMovies.value = if(genreId != null){
                mediaRepository.getTrendingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTrendingMovies().cachedIn(viewModelScope)
            }
        }
    }
    fun getTrendingSeries(genreId : Int? ) {
        viewModelScope.launch {
            _getTrendingSeries.value = if(genreId != null){
                mediaRepository.getTrendingSeries().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTrendingSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedMovies(genreId : Int? ) {
        viewModelScope.launch {
            _getTopRatedMovies.value = if(genreId != null){
                mediaRepository.getTopRatedMovies().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTopRatedMovies().cachedIn(viewModelScope)
            }
        }
    }
    fun getTopRatedSeries(genreId : Int? ) {
        viewModelScope.launch {
            _getTopRatedSeries.value = if(genreId != null){
                mediaRepository.getTopRatedSeries().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTopRatedSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getNowPlayingMovies(genreId : Int? ) {
        viewModelScope.launch {
            _getNowPlayingMovies.value = if(genreId != null){
                mediaRepository.getNowPlayingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getNowPlayingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getAiringTodaySeries(genreId : Int? ) {
        viewModelScope.launch {
            _getAiringTodaySeries.value = if(genreId != null){
                mediaRepository.getAiringTodaySeries().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getAiringTodaySeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getUpcomingMovies(genreId : Int? ) {
        viewModelScope.launch {
            _getUpcomingMovies.value = if(genreId != null){
                mediaRepository.getUpcomingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getSimilarMovies(mediaId : Int ) {
        viewModelScope.launch {
            _getSimilarMovies.value = mediaRepository
                .getSimilarMovies(mediaId)
                .cachedIn(viewModelScope)
        }
    }

    fun getSimilarSeries(mediaId : Int ) {
        viewModelScope.launch {
            _getSimilarSeries.value = mediaRepository
                .getSimilarSeries(mediaId)
                .cachedIn(viewModelScope)
        }
    }

    fun getRecommendedMovies(mediaId : Int) {
        viewModelScope.launch {
            _getRecommendedMovies.value = mediaRepository
                .getRecommendedMovies(mediaId)
                .cachedIn(viewModelScope)
        }
    }


    fun getRecommendedSeries(mediaId: Int) {
        viewModelScope.launch {
            _getRecommendedSeries.value = mediaRepository
                .getRecommendedSeries(mediaId)
                .cachedIn(viewModelScope)
        }
    }



    private fun getTVGenres() {
        viewModelScope.launch {
            when(val result = genreRepository.getTVGenres()){
                is Resource.Success -> {
                    _getTVGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            when(val result = genreRepository.getMovieGenres()){
                is Resource.Success -> {
                    _getMovieGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }
    }


    fun setSelectedOption(selected : String){
        _selectedOption.value = selected
    }

    fun setGenre(genre : String){
        _selectedGenre.value = genre
    }

    fun filterByGenre(genreId : Int?, type : String){
        when(type){
            MOVIE_TAB ->{
                getTrendingMovies(genreId)
                getPopularMovies(genreId)
                getTopRatedMovies(genreId)
                getNowPlayingMovies(genreId)
                getUpcomingMovies(genreId)
            }
            TV_SHOW_TAB ->{
                getTrendingSeries(genreId)
                getPopularSeries(genreId)
                getTopRatedSeries(genreId)
                getAiringTodaySeries(genreId)
            }
        }
    }

}