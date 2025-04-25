package com.rksrtx76.cinemax.data.repository

import com.rksrtx76.cinemax.data.preferences.UserPreferences
import com.rksrtx76.cinemax.domain.repository.PreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepositoryImpl @Inject constructor(
    private val userPreferences : UserPreferences
) : PreferenceRepository {

    override fun getIncludeAdult() : Flow<Boolean?> = userPreferences.includeAdultFlow

    override suspend fun updateIncludeAdult(includeAdult : Boolean){
        userPreferences.updateIncludeAdult(includeAdult)
    }
}