package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.domain.repository.ReviewRepository
import com.rksrtx76.cinemax.data.model.Review
import com.rksrtx76.cinemax.paging.ReviewSource
import com.rksrtx76.cinemax.util.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : ReviewRepository {
    override fun getMediaReviews(mediaId : Int, mediaType : MediaType) : Flow<PagingData<Review>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                ReviewSource(api = mediaApiService, mediaId = mediaId, mediaType = mediaType)
            }
        ).flow
    }
}