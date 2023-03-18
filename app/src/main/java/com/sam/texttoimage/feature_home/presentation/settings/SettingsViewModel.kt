package com.sam.texttoimage.feature_home.presentation.settings

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.texttoimage.feature_home.data.dataStore
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.data.model.Resolution
import com.sam.texttoimage.feature_home.domain.AppPreferenceRepository
import com.sam.texttoimage.ui.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appPreferenceRepository: AppPreferenceRepository
):ViewModel(){

    fun setTheme(theme: Theme){
        viewModelScope.launch {
            appPreferenceRepository.setTheme(theme)
        }
    }

    fun setResolution(resolution: Resolution){
        viewModelScope.launch {
            appPreferenceRepository.setResolution(resolution)
        }
    }
    val preferences = appPreferenceRepository.appPreferences
}



