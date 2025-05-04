package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchResult = mutableStateOf<Flow<PagingData<Search>>>(emptyFlow())
    val searchResult = _searchResult

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query : String){
        _searchQuery.value = query
        search(query)
    }

    fun search(query : String){
        viewModelScope.launch(Dispatchers.IO) {
            _searchResult.value = searchRepository.multiSearch(query).map { pagingData ->
                pagingData.filter {
                    ((it.title != null || it.originalName != null || it.originalTitle != null) &&
                            (it.mediaType == "movie"|| it.mediaType == "tv"))
                }
            }.cachedIn(viewModelScope)
        }
    }
}
