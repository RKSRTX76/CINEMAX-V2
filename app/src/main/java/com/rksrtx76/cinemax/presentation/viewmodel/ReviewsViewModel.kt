package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rksrtx76.cinemax.data.repository.ReviewRepositoryImpl
import com.rksrtx76.cinemax.data.model.Review
import com.rksrtx76.cinemax.util.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val reviewRepositoryImpl: ReviewRepositoryImpl
) : ViewModel() {
    private val _mediaReviews = MutableStateFlow<Flow<PagingData<Review>>>(emptyFlow())
    val mediaReviews = _mediaReviews.asStateFlow()

    fun getMediaReview(mediaId : Int, mediaType: MediaType){
        viewModelScope.launch {
            _mediaReviews.value = reviewRepositoryImpl.getMediaReviews(mediaId, mediaType).cachedIn(viewModelScope)
        }
    }
}