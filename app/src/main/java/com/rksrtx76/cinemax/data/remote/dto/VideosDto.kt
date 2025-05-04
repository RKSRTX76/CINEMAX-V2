package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.cinemax.data.model.Video

data class VideosDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("results")
    val results : List<Video> = emptyList()
)
