package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.domain.repository.CastRepository
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val castRepository: CastRepository
) : ViewModel(){

    private val _movieDetails = mutableStateOf<Resource<MediaDetails>>(Resource.Loading())
    val movieDetails = _movieDetails

    private val _seriesDetails = mutableStateOf<Resource<MediaDetails>>(Resource.Loading())
    val seriesDetails = _seriesDetails

    // State for Cast
    private val _castDetails = mutableStateOf<Resource<CastResponseDto>>(Resource.Loading())
    val castDetails = _castDetails

    fun getMovieDetails(mediaId: Int) {
        viewModelScope.launch {
            val result = mediaRepository.getMovieDetails(mediaId)
            _movieDetails.value = result
        }
    }

    fun getSeriesDetails(mediaId: Int) {
        viewModelScope.launch {
            val result = mediaRepository.getTVDetails(mediaId)
            _seriesDetails.value = result
        }
    }

    fun getMovieCastDetails(mediaId: Int) {
        viewModelScope.launch {
            val result = castRepository.getMovieCastDetails(mediaId)
            Timber.d("DetailsViewModel - Cast, ${result.data}")
            _castDetails.value = result
        }
    }
    fun getSeriesCastDetails(mediaId: Int) {
        viewModelScope.launch {
            val result = castRepository.getSeriesCastDetails(mediaId)
            Timber.d("DetailsViewModel - Cast, ${result.data}")
            _castDetails.value = result
        }
    }
}