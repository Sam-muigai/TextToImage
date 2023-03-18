package com.sam.texttoimage.feature_home.data.model

import com.sam.texttoimage.ui.theme.Theme
import kotlinx.serialization.Serializable

@Serializable
data class AppPreferences(
    val theme: Theme = Theme.LIGHT_THEME,
    val resolution: Resolution = Resolution.LOW_RESOLUTION
)

enum class Resolution(val resolution: String) {
    HIGH_RESOLUTION(
        "1024x1024"
    ),
    MEDIUM_RESOLUTION(
        "512x512"
    ),
    LOW_RESOLUTION(
        "256x256"
    )
}
