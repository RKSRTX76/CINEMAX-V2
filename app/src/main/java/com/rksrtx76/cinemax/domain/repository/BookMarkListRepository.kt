package com.rksrtx76.cinemax.domain.repository

import com.rksrtx76.cinemax.data.local.MediaEntity
import kotlinx.coroutines.flow.Flow

interface BookMarkListRepository {
    suspend fun insertToBookMarkList(media : MediaEntity)
    suspend fun removeFromBookMarkList(mediaId : Int)
    suspend fun exists(mediaId : Int) : Int
    fun getBookMarkList() : Flow<List<MediaEntity>>
    suspend fun deleteFromBookMarkList()
}