package com.example.jetpackcompose.text

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimpleTextComposableTest {

    // This test rule allows us to run tests for compose without having to do any manual setup.
    // This also creates a blank activity for you so that you can present you composable inside it.
    // If you want to use of a custom activity, you can use AndroidComposeTestRule instead.

    // Note: One last thing that we need to do in order to use the default compose rule is that we
    // need to add the blank activity that it uses to the AndroidManifest.xml. Search for
    // androidx.activity.ComponentActivity in the AndroidManifest.xml
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // We pass the composable that we want to test to the test rule using the setContent
        // method, similar to how we set a composable in an activity. One thing to note is that
        // you can use the setContent method only once per test otherwise it will throw an
        // IllegalStateException.

        // We then initialize the composable we want to test (SimpleText) & use it to set the
        // content of the compose test rule.
        composeTestRule.setContent { SimpleText("Learn Jetpack Compose By Example") }
    }

    @Test
    fun check_if_app_launched_and_displayed_text() {
        // findByText is a helper method that looks for a composable component that contains the
        // text passed to it. It returns a SemanticsNodeInteraction, which allows us to do a
        // bunch of checks(isDisplayed, isToggelable, etc) and interactions(like click, scroll, etc)


        // In this example, we just check if there is a composable with the text
        // "Learn Jetpack Compose By Example"
        composeTestRule.onNodeWithText("Learn Jetpack Compose By Example").assertIsDisplayed()
    }
}
