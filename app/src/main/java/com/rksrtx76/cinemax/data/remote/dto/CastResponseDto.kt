package com.rksrtx76.cinemax.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rksrtx76.CINEMAX.model.CastDetails

data class CastResponseDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("cast")
    val casts : List<CastDetails>
)