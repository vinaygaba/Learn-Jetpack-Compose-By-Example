package com.example.jetpackcompose.navigation.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.jetpackcompose.navigation.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationComposeWithArgumentNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            ComposeNavigationWithArgApp(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        navController
            .assertCurrentRouteName("tasks")
    }

    @Test
    fun verifyBackNavigationNotShownOnStartDestination() {
        val backText = "Back"

        composeTestRule
            .onNodeWithContentDescription(backText)
            .assertDoesNotExist()
    }

    @Test
    fun clickTask_navigatesToTaskDetailsScreen() {
        navController.assertCurrentRouteName("tasks")

        composeTestRule
            .onNodeWithText("Contribute to Learn-Jetpack-Compose-By-Example")
            .performClick()

        navController.assertCurrentRouteName("tasks/{taskId}")
    }

    @Test
    fun clickNavigateUpButton_navigatesToTaskListScreen() {
        navController.assertCurrentRouteName("tasks")

        navigateToTaskDetails()

        navController.assertCurrentRouteName("tasks/{taskId}")

        val backText = "Back"

        composeTestRule
            .onNodeWithContentDescription(backText)
            .performClick()

        navController.assertCurrentRouteName("tasks")
    }

    private fun navigateToTaskDetails() {
        composeTestRule
            .onNodeWithText("Contribute to Learn-Jetpack-Compose-By-Example")
            .performClick()
    }

}