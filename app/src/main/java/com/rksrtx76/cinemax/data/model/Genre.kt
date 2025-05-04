package com.rksrtx76.CINEMAX.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Genre(
    @SerializedName("id")
    val id : Int?,
    @SerializedName("name")
    val name : String
)
