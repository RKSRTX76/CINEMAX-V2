package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.local.BookMarkDataBase
import com.rksrtx76.cinemax.data.local.BookMark
import com.rksrtx76.cinemax.domain.repository.BookMarkListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookMarkListRepositoryImpl @Inject constructor(
    private val bookmarkDatabase : BookMarkDataBase
) : BookMarkListRepository {
    override fun getBookMarkList(): Flow<List<BookMark>> {
        return bookmarkDatabase.bookmarkDao.getBookMarkList()
    }

    override fun getBookMark(mediaId: Int): BookMark {
        return bookmarkDatabase.bookmarkDao.getABookmark(mediaId)
    }

    override suspend fun insertToBookMarkList(media: BookMark) {
        return bookmarkDatabase.bookmarkDao.insertToBookMarkList(media)
    }

    override suspend fun exists(mediaId: Int): Int {
        return bookmarkDatabase.bookmarkDao.exists(mediaId)
    }

    override suspend fun removeFromBookMarkList(mediaId: Int) {
        return bookmarkDatabase.bookmarkDao.removeFromBookMarkList(mediaId)
    }

    override suspend fun deleteFromBookMarkList() {
        return bookmarkDatabase.bookmarkDao.deleteBookMarkList()
    }

}