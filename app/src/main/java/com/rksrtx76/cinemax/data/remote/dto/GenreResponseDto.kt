package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.CINEMAX.model.Genre

data class GenreResponseDto(
    @SerializedName("genres")
    val genres: List<Genre>
)
