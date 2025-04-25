package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.local.BookMarkDataBase
import com.rksrtx76.cinemax.data.local.MediaEntity
import com.rksrtx76.cinemax.domain.repository.BookMarkListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookMarkListRepositoryImpl @Inject constructor(
    private val database : BookMarkDataBase
) : BookMarkListRepository {
    override suspend fun insertToBookMarkList(media : MediaEntity){
        database.bookmarkDao.insertToBookMarkList(media)
    }

    override suspend fun removeFromBookMarkList(mediaId : Int){
        database.bookmarkDao.removeFromBookMarkList(mediaId)
    }

    override suspend fun exists(mediaId : Int) : Int{
        return database.bookmarkDao.exists(mediaId)
    }

    override fun getBookMarkList() : Flow<List<MediaEntity>>{
        return database.bookmarkDao.getBookMarkList()
    }

    override suspend fun deleteFromBookMarkList(){
        database.bookmarkDao.deleteBookMarkList()
    }
}