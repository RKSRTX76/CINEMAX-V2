package com.rksrtx76.cinemax.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_list")
data class MediaEntity(
    @PrimaryKey val mediaId : Int,
    val imagePath : String?,
    val title : String,
    val releaseDate : String,
    val rating : Double,
    val addedOn : String
)
