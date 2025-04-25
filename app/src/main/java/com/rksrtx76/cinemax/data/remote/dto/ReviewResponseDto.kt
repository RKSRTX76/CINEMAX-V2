package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.model.Review

data class ReviewResponseDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Review>,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int,
)
