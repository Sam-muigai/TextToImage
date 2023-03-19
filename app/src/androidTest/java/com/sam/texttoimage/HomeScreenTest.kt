package com.sam.texttoimage

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sam.texttoimage.feature_home.domain.model.fakeData
import com.sam.texttoimage.feature_home.presentation.home.HomeScreen
import com.sam.texttoimage.feature_home.presentation.home.HomeScreenState
import com.sam.texttoimage.ui.theme.TextToImageTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    private val lightTheme = AppCompatDelegate.MODE_NIGHT_NO

    @Test
    fun search_field_shows_initially() {
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Empty,
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { },
                    onSearch = { },
                    onSettingsClicked = { },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithText("Type a prompt")
            .assertIsDisplayed()
    }

    @Test
    fun error_animation_shows_when_error_occurs() {
        composeTestRule.setContent {

            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Error("Error occurred."),
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Error Animation")
            .assertIsDisplayed()
    }

    @Test
    fun loading_animation_shows_when_loading() {
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Loading,
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun search_field_is_not_focused_initially() {
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Empty,
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithText("Type a prompt")
            .assertIsNotFocused()
    }

    @Test
    fun when_search_is_clicked_it_gain_focus() {
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Empty,
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithText("Type a prompt")
            .performClick()
            .assertIsFocused()
    }

    @Test
    fun when_search_field_has_text_clear_icon_is_visible() {
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Empty,
                    onImageClicked = {},
                    value = "Cow eating grass",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Clear text")
            .assertIsDisplayed()
    }


    @Test
    fun when_successful_images_show_successful(){
        composeTestRule.setContent {
            TextToImageTheme(theme = lightTheme) {
                HomeScreen(
                    homeScreenState = HomeScreenState.Success(fakeData),
                    onImageClicked = {},
                    value = "",
                    onClearClicked = { /*TODO*/ },
                    onSearch = { /*TODO*/ },
                    onSettingsClicked = { /*TODO*/ },
                    onValueChange = {}
                )
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Generated Images")
            .assertIsDisplayed()
    }
}