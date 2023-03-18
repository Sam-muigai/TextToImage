package com.sam.texttoimage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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


}