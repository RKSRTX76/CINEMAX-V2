package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import com.rksrtx76.CINEMAX.model.Genre

@Serializable
data class GenreResponseDto(
    @SerializedName("genres")
    val genres: List<Genre>
)
