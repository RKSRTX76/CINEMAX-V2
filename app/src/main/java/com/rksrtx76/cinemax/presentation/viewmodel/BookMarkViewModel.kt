package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.cinemax.data.local.BookMark
import com.rksrtx76.cinemax.domain.repository.BookMarkListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookMarkListRepository: BookMarkListRepository
) : ViewModel() {

    private val _bookmarkList = mutableStateOf<Flow<List<BookMark>>>(emptyFlow())
    val bookMarkList = _bookmarkList

    private val _insertToBookmark = mutableStateOf(0)
    val insertToBookmark = _insertToBookmark


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

    fun ifExists(mediaId : Int){
        viewModelScope.launch {
            _insertToBookmark.value = bookMarkListRepository.exists(mediaId)
        }
    }
}