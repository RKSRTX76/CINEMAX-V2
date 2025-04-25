package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.CINEMAX.model.Media

data class MediaResponseDto(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Media>,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int,
)
