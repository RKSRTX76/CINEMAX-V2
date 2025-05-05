package com.rksrtx76.cinemax.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookMark::class], version = 4, exportSchema = false)
abstract class BookMarkDataBase : RoomDatabase() {
    abstract val bookmarkDao : BookMarkDao
}