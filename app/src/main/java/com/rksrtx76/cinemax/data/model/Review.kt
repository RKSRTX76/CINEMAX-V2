package com.rksrtx76.cinemax.data.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("id")
    val id: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdOn: String,
    @SerializedName("url")
    val url: String
)

data class AuthorDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("avatar_path")
    val avatarPath: String?,
    @SerializedName("rating")
    val rating: Int?,
)
