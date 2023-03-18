package com.sam.texttoimage.feature_home.data

import androidx.datastore.core.DataStore
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.data.model.Resolution
import com.sam.texttoimage.feature_home.domain.AppPreferenceRepository
import com.sam.texttoimage.ui.theme.Theme
import kotlinx.coroutines.flow.Flow

class AppPreferenceRepositoryImpl(
    private val appPreference:DataStore<AppPreferences>
):AppPreferenceRepository{
    override val appPreferences: Flow<AppPreferences>
        get() = appPreference.data

    override suspend fun setTheme(theme: Theme) {
        appPreference.updateData {
            it.copy(
                theme = theme
            )
        }
    }

    override suspend fun setResolution(resolution: Resolution) {
        appPreference.updateData {
            it.copy(
                resolution = resolution
            )
        }
    }
}