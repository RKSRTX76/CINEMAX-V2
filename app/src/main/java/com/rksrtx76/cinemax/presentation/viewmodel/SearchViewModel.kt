package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.repository.PreferenceRepositoryImpl
import com.rksrtx76.cinemax.data.repository.SearchRepositoryImpl
import com.rksrtx76.cinemax.domain.repository.PreferenceRepository
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<Flow<PagingData<Search>>>(emptyFlow())
    val searchResults = _searchResults.asStateFlow()

    private val _includeAdult = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            preferenceRepository.getIncludeAdult().collect { includeAdultPref ->
                _includeAdult.value = includeAdultPref ?: true
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        searchMedia()
    }

    private fun searchMedia() {
        viewModelScope.launch {
            if (_searchQuery.value.isNotBlank()) {
                _searchResults.value = searchRepository.multiSearch(
                    searchParams = _searchQuery.value,
                    includeAdult = _includeAdult.value
                ).map { pagingData ->
                    pagingData.filter { media ->
                        (media.title != null || media.originalName != null || media.originalTitle != null)
                                && (media.mediaType == "movie" || media.mediaType == "tv")
                    }
                }.cachedIn(viewModelScope)
            }
        }
    }
}
