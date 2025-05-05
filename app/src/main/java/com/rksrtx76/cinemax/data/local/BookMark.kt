package com.rksrtx76.cinemax.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_list")
data class BookMark(
    @PrimaryKey val mediaId : Int,
    val imagePath : String?,
    val title : String,
    val mediaType : String,
    val releaseDate : String,
    val description : String,
    val rating : String,
//    val addedOn : String
)
