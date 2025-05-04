package com.rksrtx76.cinemax.presentation.viewmodel

import com.rksrtx76.CINEMAX.model.Genre

sealed class MediaDetailsScreenEvents {
    object NavigateToWatchVideo : MediaDetailsScreenEvents()
    object Refresh : MediaDetailsScreenEvents()
    data class SetDataAndLoad(val mediaId: Int, val mediaType: String) : MediaDetailsScreenEvents()
    data class ToggleBookmark(val mediaId: Int) : MediaDetailsScreenEvents()
}
