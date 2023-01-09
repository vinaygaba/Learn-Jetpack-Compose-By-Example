package com.example.jetpackcompose.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            App(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        navController
            .assertCurrentRouteName(AppScreen.ScreenA.name)
    }

    @Test
    fun verifyBackNavigationNotShownOnScreenA() {
        val backTest = "Back"
        composeTestRule
            .onNodeWithText(backTest)
            .assertDoesNotExist()
    }

    @Test
    fun clickNavigateToScreenB_navigatesToScreenBScreen() {
        navController.assertCurrentRouteName(AppScreen.ScreenA.name)

        composeTestRule
            .onNodeWithText("Navigate to Screen B")
            .performClick()

        navController.assertCurrentRouteName(AppScreen.ScreenB.name)
    }

    @Test
    fun clickNavigateToScreenA_navigatesToScreenAScreen() {
        navController.assertCurrentRouteName(AppScreen.ScreenA.name)

        navigateToScreenB()

        composeTestRule
            .onNodeWithText("Navigate to Screen A")
            .performClick()

        navController.assertCurrentRouteName(AppScreen.ScreenA.name)
    }

    @Test
    fun clickNavigateToScreenC_navigatesToScreenCScreen() {
        navController.assertCurrentRouteName(AppScreen.ScreenA.name)

        navigateToScreenB()

        composeTestRule
            .onNodeWithText("Navigate to Screen C")
            .performClick()

        navController.assertCurrentRouteName(AppScreen.ScreenC.name)
    }

    @Test
    fun clickNavigateToScreenB_navigatesToScreenBScreenFromScreenC() {
        navController.assertCurrentRouteName(AppScreen.ScreenA.name)

        navigateToScreenC()

        navController.assertCurrentRouteName(AppScreen.ScreenC.name)

        composeTestRule
            .onNodeWithText("Navigate to Screen B")
            .performClick()

        navController.assertCurrentRouteName(AppScreen.ScreenB.name)
    }

    @Test
    fun clickNavigateToScreenA_navigatesToScreenAScreenFromScreenC() {
        navController.assertCurrentRouteName(AppScreen.ScreenA.name)

        navigateToScreenC()

        navController.assertCurrentRouteName(AppScreen.ScreenC.name)

        composeTestRule
            .onNodeWithText("Navigate to Screen A (PopupTo with Inclusive)")
            .performClick()

        navController.assertCurrentRouteName(AppScreen.ScreenA.name)
    }

    private fun navigateToScreenB() {
        composeTestRule
            .onNodeWithText("Navigate to Screen B")
            .performClick()
    }

    private fun navigateToScreenC() {
        navigateToScreenB()
        composeTestRule
            .onNodeWithText("Navigate to Screen C")
            .performClick()
    }
}
