package com.rksrtx76.cinemax.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {

    @Query("SELECT * FROM bookmark_list ORDER BY mediaid DESC")
    fun getBookMarkList() : Flow<List<BookMark>>

    @Query("SELECT * FROM bookmark_list WHERE mediaId =:mediaId")
    fun getABookmark(mediaId : Int) : BookMark

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToBookMarkList(bookmark : BookMark)

    @Query("DELETE FROM bookmark_list WHERE mediaId = :mediaId")
    suspend fun removeFromBookMarkList(mediaId : Int)

    @Query("DELETE FROM bookmark_list")
    suspend fun deleteBookMarkList()

    @Query("SELECT EXISTS(SELECT 1 FROM bookmark_list WHERE mediaId = :mediaId)")
    suspend fun exists(mediaId : Int) : Int

}