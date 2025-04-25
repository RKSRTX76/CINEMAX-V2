package com.rksrtx76.cinemax.domain.repository

import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(searchParams : String, includeAdult : Boolean) : Flow<PagingData<Search>>
}