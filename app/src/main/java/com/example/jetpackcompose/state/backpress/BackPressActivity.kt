package com.example.jetpackcompose.state.backpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.remember
import androidx.compose.setValue
import androidx.ui.core.LifecycleOwnerAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class BackPressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Reacting to state changes is core to how Jetpack Compose works. This state variable 
            // "appState" is used to maintain the current active screen. The value is updated
            // based on the actions of the user across the different screens. Every time the value
            // of this variable changes, the relevant sub-composables that depends on it is 
            // automatically updated/recomposed.
            
            // remember{} is a helper composable that calculates the value passed to it only 
            // during the first composition. It then returns the same value for every subsequent 
            // composition. In the example below, it initializes the value of AppState() and does
            // it only during the first composition. It's important to understand that the 
            // subsequent screens where this value is passed to are still allowed to modify the 
            // value (depending on whether it has mutable properties).  
            val appState = remember { AppState() }
            BackPressApp(appState)
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun BackPressApp(appState: AppState) {
    // Choose which screen to show based on the value in the currentScreen variable inside AppState
    when (appState.currentScreen) {
        CurrentScreen.SCREEN1 -> Screen1(appState)
        CurrentScreen.SCREEN2 -> Screen2(appState)
        CurrentScreen.SCREEN3 -> Screen3(appState)
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun Screen1(appState: AppState) {
    val activity = (LifecycleOwnerAmbient.current as ComponentActivity)
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. We give it a ContentGravity of Center to ensure the children of this composable
    // are placed in its center. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize().
    Box(
        modifier = Modifier.fillMaxSize() + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        // TitleComponent is a composable we created in one of the files that merely renders 
        // text on the screen. 
        TitleComponent(title = "This is Screen 1")
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(
            backgroundColor = Color.Gray,
            onClick = {
                appState.currentScreen = CurrentScreen.SCREEN2
            }) {
            TitleComponent(title = "Go To Screen 2")
        }
        TitleComponent(title = "Press back to exit this activity")
    }
    
    BackButtonHandler {
        activity.finish()
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun Screen2(appState: AppState) {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. We give it a ContentGravity of Center to ensure the children of this composable
    // are placed in its center. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize().
    Box(
        modifier = Modifier.fillMaxSize() + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        // TitleComponent is a composable we created in one of the files that merely renders 
        // text on the screen. 
        TitleComponent(title = "This is Screen 2")
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(
            backgroundColor = Color.Gray,
            onClick = {
                appState.currentScreen = CurrentScreen.SCREEN3
            }) {
            TitleComponent(title = "Go To Screen 3")
        }
        TitleComponent(title = "Press back to go to Screen 1")
    }
    BackButtonHandler {
        appState.currentScreen = CurrentScreen.SCREEN1
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun Screen3(appState: AppState) {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. We give it a ContentGravity of Center to ensure the children of this composable
    // are placed in its center. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize().
    Box(
        modifier = Modifier.fillMaxSize() + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        // TitleComponent is a composable we created in one of the files that merely renders 
        // text on the screen. 
        TitleComponent(title = "This is Screen 3")
        // TitleComponent is a composable we created in one of the files that merely renders 
        // text on the screen. 
        TitleComponent(title = "You can only go back from here. Press back to go to Screen 2.")
    }
    BackButtonHandler {
        appState.currentScreen = CurrentScreen.SCREEN2
    }
}

// Simple class that we created to hold the value for the current active screen.
class AppState {
    var currentScreen by mutableStateOf(CurrentScreen.SCREEN1)
}

enum class CurrentScreen {
    SCREEN1,
    SCREEN2,
    SCREEN3
}
