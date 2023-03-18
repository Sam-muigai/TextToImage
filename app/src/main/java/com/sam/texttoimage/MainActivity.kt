package com.sam.texttoimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.sam.texttoimage.feature_home.presentation.NavGraphs
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.presentation.settings.SettingsViewModel
import com.sam.texttoimage.ui.theme.TextToImageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val themes = viewModel.preferences.collectAsState(initial = AppPreferences()).value
            TextToImageTheme(theme = themes.theme.themeValue) {
              DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}










