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
import kotlinx.coroutines.flow.emptyFlow
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


    init {
        getBookmarkList()
    }

    fun addToBookmarkList(bookmark : BookMark){
        viewModelScope.launch {
            bookMarkListRepository.insertToBookMarkList(bookmark)
        }.invokeOnCompletion {
            ifExists(bookmark.mediaId)
        }
    }

    private fun getBookmarkList(){
        _bookmarkList.value = bookMarkListRepository.getBookMarkList()
    }

    fun deleteAllBookMark(){
        viewModelScope.launch {
            bookMarkListRepository.deleteFromBookMarkList()
        }
    }

    fun removeABookmark(mediaId : Int){
        viewModelScope.launch {
            bookMarkListRepository.removeFromBookMarkList(mediaId)
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
}