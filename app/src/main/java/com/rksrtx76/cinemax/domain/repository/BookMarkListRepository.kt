package com.rksrtx76.cinemax.domain.repository

import com.rksrtx76.cinemax.data.local.BookMark
import kotlinx.coroutines.flow.Flow

interface BookMarkListRepository {
    fun getBookMarkList() : Flow<List<BookMark>>
    fun getBookMark(mediaId : Int) : BookMark
    suspend fun insertToBookMarkList(media : BookMark)
    suspend fun exists(mediaId : Int) : Int
    suspend fun removeFromBookMarkList(mediaId : Int)
    suspend fun deleteFromBookMarkList()
}