package com.rksrtx76.cinemax.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getIncludeAdult() : Flow<Boolean?>
    suspend fun updateIncludeAdult(includeAdult : Boolean)
}