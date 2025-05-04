package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import com.rksrtx76.cinemax.paging.SearchMediaSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService,
) : SearchRepository {
    override fun multiSearch(searchParams : String) : Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SearchMediaSource(mediaApiService, searchParams)
            }
        ).flow
    }
}