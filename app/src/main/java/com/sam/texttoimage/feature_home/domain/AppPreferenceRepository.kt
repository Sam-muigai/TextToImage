package com.sam.texttoimage.feature_home.domain

import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.data.model.Resolution
import com.sam.texttoimage.ui.theme.Theme
import kotlinx.coroutines.flow.Flow

interface AppPreferenceRepository{

    val appPreferences:Flow<AppPreferences>

    suspend fun setTheme(theme:Theme)

    suspend fun setResolution(resolution: Resolution)

}