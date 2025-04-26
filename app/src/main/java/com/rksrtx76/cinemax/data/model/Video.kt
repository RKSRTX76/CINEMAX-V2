package com.rksrtx76.cinemax.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    @SerializedName("key")
    val key: String? = null,

    @SerializedName("site")
    val site: String? = null,

    @SerializedName("id")
    val id: String,

    @SerializedName("iso_3166_1")
    val iso31661: String,

    @SerializedName("iso_639_1")
    val iso6391: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("official")
    val official: Boolean,

    @SerializedName("published_at")
    val publishedAt: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("type")
    val type: String
) : Parcelable
