package com.rksrtx76.cinemax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.cinemax.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrefsViewModel @Inject constructor(
    private val preferenceRepositoryImpl: PreferenceRepositoryImpl
) : ViewModel() {
    fun updateIncludeAdult(includeAdult : Boolean){
        viewModelScope.launch {
            preferenceRepositoryImpl.updateIncludeAdult(includeAdult)
        }
    }
    val includeAdult = MutableStateFlow(preferenceRepositoryImpl.getIncludeAdult())
}