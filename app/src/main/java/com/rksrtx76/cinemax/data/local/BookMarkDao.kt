package com.rksrtx76.cinemax.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToBookMarkList(media : MediaEntity)

    @Query("DELETE FROM bookmark_list WHERE mediaId = :mediaId")
    suspend fun removeFromBookMarkList(mediaId : Int)

    @Query("DELETE FROM bookmark_list")
    suspend fun deleteBookMarkList()

    @Query("SELECT EXISTS(SELECT 1 FROM bookmark_list WHERE mediaId = :mediaId)")
    suspend fun exists(mediaId : Int) : Int

    @Query("SELECT * FROM bookmark_list ORDER BY addedOn DESC")
    fun getBookMarkList() : Flow<List<MediaEntity>>
}