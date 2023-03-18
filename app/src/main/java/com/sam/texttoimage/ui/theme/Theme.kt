package com.sam.texttoimage.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = LightBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TextToImageTheme(
    theme:Int,
    content: @Composable () -> Unit
) {
    val autoColors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    val colors = when(theme){
        Theme.DARK_THEME.themeValue -> DarkColorPalette
        Theme.LIGHT_THEME.themeValue -> LightColorPalette
        else ->autoColors
    }
    val systemUiController = rememberSystemUiController()
    val darkIcons = !isSystemInDarkTheme() && theme == Theme.LIGHT_THEME.themeValue


    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

enum class Theme(
    val themeValue: Int,
    val themeName:String
) {
    FOLLOW_SYSTEM(
        themeValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        themeName = "Default"
    ),
    LIGHT_THEME(
        themeValue = AppCompatDelegate.MODE_NIGHT_NO,
        themeName = "Light"
    ),
    DARK_THEME(
        themeValue = AppCompatDelegate.MODE_NIGHT_YES,
        themeName = "Dark"
    )
}