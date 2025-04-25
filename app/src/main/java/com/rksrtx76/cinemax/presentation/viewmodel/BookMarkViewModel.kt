package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.cinemax.data.local.MediaEntity
import com.rksrtx76.cinemax.data.repository.BookMarkListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookMarkListRepositoryImpl: BookMarkListRepositoryImpl
) : ViewModel() {
    private val _addedToBookMarkList = MutableStateFlow(0)  // stores media id of added media to bookmark list
    val addedToBookMarkList = _addedToBookMarkList.asStateFlow()

    private val _bookMarkList = mutableStateOf<Flow<List<MediaEntity>>>(emptyFlow())
    val bookMarkList = _bookMarkList

    init {
        getBookMarkList()
    }

    fun addToBookMarkList(media : MediaEntity){
        viewModelScope.launch {
            // inserts into Room DB
            bookMarkListRepositoryImpl.insertToBookMarkList(media)
            // checks if it was successfully added
            exists(mediaId = media.mediaId)
        }
    }

    fun exists(mediaId : Int){
        viewModelScope.launch {
            // insert mediaId to bookmark list if not exist then store 0
            _addedToBookMarkList.value = bookMarkListRepositoryImpl.exists(mediaId)
        }
    }

    fun removeFromBookmarkList(mediaId : Int){
        viewModelScope.launch {
            bookMarkListRepositoryImpl.removeFromBookMarkList(mediaId)
            // check if removed or not
            exists(mediaId)
        }
    }

    private fun getBookMarkList(){
        _bookMarkList.value = bookMarkListRepositoryImpl.getBookMarkList()
    }

    fun deleteBookMarkList(){
        viewModelScope.launch {
            bookMarkListRepositoryImpl.deleteFromBookMarkList()
        }
    }
}