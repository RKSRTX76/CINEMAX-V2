package com.rksrtx76.CINEMAX.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rksrtx76.cinemax.data.model.Video
import kotlinx.parcelize.Parcelize


data class Media(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val release_date: String,
    @SerializedName("title", alternate = ["name"])
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
)