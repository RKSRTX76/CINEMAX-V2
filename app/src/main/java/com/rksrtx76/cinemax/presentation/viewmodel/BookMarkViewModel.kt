package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.cinemax.data.local.BookMark
import com.rksrtx76.cinemax.domain.repository.BookMarkListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookMarkListRepository: BookMarkListRepository
) : ViewModel() {

    private val _bookmarkList = mutableStateOf<Flow<List<BookMark>>>(emptyFlow())
    val bookMarkList = _bookmarkList

    private val _insertToBookmark = MutableStateFlow(0)
    val insertToBookmark : StateFlow<Int> = _insertToBookmark.asStateFlow()

    private val _randomMovieId = MutableStateFlow<Int?>(null)
    val randomMovieId: StateFlow<Int?> = _randomMovieId.asStateFlow()

    private val _randomTvId = MutableStateFlow<Int?>(null)
    val randomTvId: StateFlow<Int?> = _randomTvId.asStateFlow()


    init {
        getBookmarkList()
//        refreshRandomMediaIds()
    }

    fun addToBookmarkList(bookmark : BookMark){
        viewModelScope.launch {
            bookMarkListRepository.insertToBookMarkList(bookmark)
            refreshRandomMediaIds()
        }.invokeOnCompletion {
            ifExists(bookmark.mediaId)
        }
    }

    fun getBookmarkList(){
        _bookmarkList.value = bookMarkListRepository.getBookMarkList()
        refreshRandomMediaIds()
    }

    fun deleteAllBookMark(){
        viewModelScope.launch {
            bookMarkListRepository.deleteFromBookMarkList()
            refreshRandomMediaIds()
        }
    }

    fun removeABookmark(mediaId : Int){
        viewModelScope.launch {
            bookMarkListRepository.removeFromBookMarkList(mediaId)
            refreshRandomMediaIds()
        }.invokeOnCompletion {
            ifExists(mediaId)
        }
    }

    fun ifExists(mediaId : Int){
        viewModelScope.launch {
//            _insertToBookmark.value = bookMarkListRepository.exists(mediaId)
            val exists = bookMarkListRepository.exists(mediaId)
            _insertToBookmark.emit(exists)
        }
    }


//    private fun refreshRandomMediaIds() {
//        viewModelScope.launch {
//            bookMarkListRepository.getBookMarkList()
//                .collect{ bookmarks ->
//                    val movies = bookmarks.filter { it.mediaType == "movie" }
//                    val tvShows = bookmarks.filter { it.mediaType == "tv" }
//
//                    _randomMovieId.emit(movies.randomOrNull()?.mediaId)
//                    _randomTvId.emit(tvShows.randomOrNull()?.mediaId)
//                }
//        }
//    }
    fun refreshRandomMediaIds() {
        viewModelScope.launch {
            val bookmarks = bookMarkListRepository.getBookMarkList().first() // Suspends until first list is available

            val movies = bookmarks.filter { it.mediaType == "movie" }
            val tvShows = bookmarks.filter { it.mediaType == "tv" }

            _randomMovieId.emit(movies.randomOrNull()?.mediaId)
            _randomTvId.emit(tvShows.randomOrNull()?.mediaId)
        }
    }
}