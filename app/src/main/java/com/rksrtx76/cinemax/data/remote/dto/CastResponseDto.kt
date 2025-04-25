package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.CINEMAX.model.Cast

data class CastResponseDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("cast")
    val castResult : List<Cast>
)