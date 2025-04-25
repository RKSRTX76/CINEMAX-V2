package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Cast
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.repository.MediaRepositoryImpl
import com.rksrtx76.cinemax.util.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mediaRepositoryImpl: MediaRepositoryImpl
) : ViewModel(){
    private val _similarMedia = MutableStateFlow<Flow<PagingData<Media>>>(emptyFlow())
    val similarMedia = _similarMedia.asStateFlow()

    private val _mediaCast = MutableStateFlow<List<Cast>>(emptyList())
    val mediaCast = _mediaCast.asStateFlow()

    fun getSimilarMedia(mediaId : Int, mediaType: MediaType){
        viewModelScope.launch {
            mediaRepositoryImpl.getSimilarMedia(mediaId, mediaType).also {
                _similarMedia.value = it
            }
        }
    }


}