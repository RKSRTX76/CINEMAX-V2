package com.rksrtx76.cinemax.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MediaEntity::class], version = 3, exportSchema = false)
abstract class BookMarkDataBase : RoomDatabase() {
    abstract val bookmarkDao : BookMarkDao
}