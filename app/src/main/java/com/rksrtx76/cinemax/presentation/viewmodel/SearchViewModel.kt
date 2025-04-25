package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.repository.PreferenceRepositoryImpl
import com.rksrtx76.cinemax.data.repository.SearchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl,
    private val preferenceRepositoryImpl: PreferenceRepositoryImpl
) : ViewModel(){
    private val _multiSearch = MutableStateFlow<Flow<PagingData<Search>>>(emptyFlow())
    val multiSearch = _multiSearch.asStateFlow()

    val includeAdult = MutableStateFlow(preferenceRepositoryImpl.getIncludeAdult())

    val _searchParam = MutableStateFlow("")
    val prevSearchParam = MutableStateFlow("")
    val searchParamState = _searchParam.asStateFlow()

    init {
        _searchParam.value = ""
    }

    fun searchRemoteMedia(includeAdult : Boolean){
        viewModelScope.launch {
            if(_searchParam.value.isNotEmpty()){
                _multiSearch.value = searchRepositoryImpl.multiSearch(
                    searchParams = _searchParam.value,
                    includeAdult = includeAdult
                ).map { result->
                    result.filter { media->
                        ((media.title != null || media.originalName != null || media.originalTitle != null)
                                && (media.mediaType == "movie" || media.mediaType == "tv"))
                    }
                }.cachedIn(viewModelScope)
            }
        }
    }
}