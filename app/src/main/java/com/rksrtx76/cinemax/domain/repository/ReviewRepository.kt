package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.cinemax.data.model.Review
import com.rksrtx76.cinemax.util.MediaType
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getMediaReviews(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Review>>
}