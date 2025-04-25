package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.rksrtx76.CINEMAX.model.Genre
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.domain.repository.GenreRepository
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    private var _mediaGenre = mutableStateListOf(Genre(null, "All"))
    val mediaGenre: SnapshotStateList<Genre> = _mediaGenre

    var selectedGenre: MutableState<Genre> = mutableStateOf(Genre(null, "All"))
    var selectedMediaType: MutableState<MediaType> = mutableStateOf(MediaType.MOVIE)

    private var _trendingMedia = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val trendingMedia: State<Flow<PagingData<Media>>> = _trendingMedia

    private var _popularMedia = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val popularMedia: State<Flow<PagingData<Media>>> = _popularMedia

    private var _topRatedMedia = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val topRatedMedia: State<Flow<PagingData<Media>>> = _topRatedMedia

    private var _nowPlayingMedia = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val nowPlayingMedia: State<Flow<PagingData<Media>>> = _nowPlayingMedia

    private var _upcomingMovies = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Media>>> = _upcomingMovies

    private var _recommendedMedia = mutableStateOf<Flow<PagingData<Media>>>(emptyFlow())
    val recommendedMedia: MutableState<Flow<PagingData<Media>>> = _recommendedMedia
    var randomMediaId: Int? = null

    init {
        refreshAll()
    }

    fun refreshAll(
        genreId : Int? = selectedGenre.value.id,
        mediaType: MediaType = selectedMediaType.value
    ){
        if(_mediaGenre.size == 1){
            getMediaGenre(mediaType)
        }
        if(genreId == null){
            selectedGenre.value = Genre(null, "All")
        }

        getTrendingMedia(genreId, mediaType)
        getPopularMedia(genreId, mediaType)
        getTopRatedMedia(genreId, mediaType)
        getNowPlayingMedia(genreId, mediaType)
        getUpcomingMovies(genreId)

        randomMediaId?.let{ id->
            getRecommendedMedia(id, genreId, mediaType)
        }
    }


    fun filterBySetSelectedGenre(genre : Genre){
        selectedGenre.value = genre
        refreshAll(genre.id)
    }

    fun getMediaGenre(mediaType : MediaType = selectedMediaType.value){
        viewModelScope.launch {
            val defaultGenre = Genre(null, "All")
            when(val results = genreRepository.getMediaGenre(mediaType)){
                is Resource.Error -> Timber.e("Error loading Genres")
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _mediaGenre.clear()
                    _mediaGenre.add(defaultGenre)
                    selectedGenre.value = defaultGenre
                    results.data?.genres?.forEach{
                        _mediaGenre.add(it)
                    }
                }
            }
        }
    }

//    fun updateSelectedGenre(genre: Genre) {
//        _selectedGenre.value = genre
//    }
//    fun updateSelectedMediaType(selectedMediaType: MediaType){
//        _selectedMediaType.value = selectedMediaType
//    }

    private fun getTrendingMedia(genreId: Int?, mediaType: MediaType){
        viewModelScope.launch {
            _trendingMedia.value = if(genreId != null){
                mediaRepository.getTrendingMedia(mediaType).map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTrendingMedia(mediaType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getPopularMedia(genreId: Int?, mediaType: MediaType){
        viewModelScope.launch {
            _popularMedia.value = if(genreId != null){
                mediaRepository.getPopularMedia(mediaType).map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getPopularMedia(mediaType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getTopRatedMedia(genreId: Int?, mediaType: MediaType){
        viewModelScope.launch {
            _topRatedMedia.value = if(genreId != null){
                mediaRepository.getTopRatedMedia(mediaType).map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getTopRatedMedia(mediaType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getNowPlayingMedia(genreId: Int?, mediaType: MediaType){
        viewModelScope.launch {
            _nowPlayingMedia.value = if(genreId != null){
                mediaRepository.getNowPlayingMedia(mediaType).map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getNowPlayingMedia(mediaType).cachedIn(viewModelScope)
            }
        }
    }

    fun getRecommendedMedia(mediaId : Int, genreId: Int? = null, mediaType: MediaType = selectedMediaType.value){
        viewModelScope.launch {
            _recommendedMedia.value = if(genreId != null){
                mediaRepository.getRecommendedMedia(mediaId,mediaType).map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getRecommendedMedia(mediaId,mediaType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getUpcomingMovies(genreId: Int?){
        viewModelScope.launch {
            _upcomingMovies.value = if(genreId != null){
                mediaRepository.getUpcomingMovies().map { results->
                    results.filter { movie->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            }else{
                mediaRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }
    }
}