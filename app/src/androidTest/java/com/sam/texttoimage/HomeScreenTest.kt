package com.sam.texttoimage

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sam.texttoimage.feature_home.presentation.home.HomeScreen
import com.sam.texttoimage.feature_home.presentation.home.HomeScreenState
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun search_field_shows_initially() {
        composeTestRule.setContent {
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
        composeTestRule
            .onNodeWithText("Type a prompt")
            .assertIsDisplayed()
    }

    @Test
    fun error_animation_shows_when_error_occurs(){
        composeTestRule.setContent {
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
        composeTestRule
            .onNodeWithContentDescription("Error Animation")
            .assertIsDisplayed()
    }

    @Test
    fun loading_animation_shows_when_loading(){
        composeTestRule.setContent {
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
        composeTestRule
            .onNodeWithContentDescription("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun when_search_is_clicked_it_gain_focus(){
        composeTestRule.setContent {
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
        composeTestRule
            .onNodeWithText("Type a prompt")
            .performClick()
            .assertIsFocused()
    }



}