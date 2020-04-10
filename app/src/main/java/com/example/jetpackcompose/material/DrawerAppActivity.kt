package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.offset
import androidx.ui.layout.padding
import androidx.ui.material.DrawerState
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ModalDrawerLayout
import androidx.ui.material.Surface
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Menu
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class DrawerAppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            DrawerAppComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun DrawerAppComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the drawerState. Any composable that reads the value of drawerState will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val (drawerState, onDrawerStateChange) = state { DrawerState.Closed }
    // State composable used to hold the value of the current active screen
    val currentScreen = state { DrawerAppScreen.Screen1 }

    // ModalDrawerLayout is a pre-defined composable used to provide access to destinations in
    // the app. It's a common pattern used across multiple apps where you see a drawer on the
    // left of the screen.
    ModalDrawerLayout(
        // Drawer state to denote whether the drawer should be open or closed.
        drawerState = drawerState,
        onStateChange = onDrawerStateChange,
        gesturesEnabled = drawerState == DrawerState.Opened,
        drawerContent = {
            //drawerContent takes a composable to represent the view/layout to display when the
            // drawer is open.
            DrawerContentComponent(
                // We pass a state composable that represents the current screen that's selected
                // and what action to take when the drawer is closed.
                currentScreen = currentScreen,
                closeDrawer = { onDrawerStateChange(DrawerState.Closed) }
            )
        },
        bodyContent = {
            // bodyContent takes a composable to represent the view/layout to display on the
            // screen. We select the appropriate screen based on the value stored in currentScreen.
            BodyContentComponent(
                currentScreen = currentScreen.value,
                openDrawer = {
                    onDrawerStateChange(DrawerState.Opened)
                }
            )
        }
    )
}

@Composable
fun DrawerContentComponent(
    currentScreen: MutableState<DrawerAppScreen>,
    closeDrawer: () -> Unit
) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. In addition, we
    // also provide the column with a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we configure the Column to
    // occupy the entire available width & height using the Modifier.fillMaxSize() modifier.
    Column(modifier = Modifier.fillMaxSize()) {
        // We want to have 3 rows in this column to represent the 3 screens in this activity.
        for (index in DrawerAppScreen.values().indices) {
            // Clickable wraps the child composable and enables it to react to a click through the onClick
            // callback similar to the onClick listener that we are accustomed to on Android.
            // Here, we just update the currentScreen variable to hold the appropriate value based on
            // the row that is clicked i.e if the first row is clicked, we set the value of
            // currentScreen to DrawerAppScreen.Screen1, when second row is clicked we set it to
            // DrawerAppScreen.Screen2 and so on and so forth.
            val screen = getScreenBasedOnIndex(index)
            Clickable(onClick = {
                currentScreen.value = screen
                // We also close the drawer when an option from the drawer is selected.
                closeDrawer()
            }) {
                // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from
                // the Material Design specification. It's generally used to change the background
                // color, add elevation, clip or add background shape to its children composables.
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    // We set the color of the row based on whether that row represents the current
                    // screen that's selected. We only want to highlight the row that's selected.
                    color = if (currentScreen.value == screen) {
                        MaterialTheme.colors.secondary
                    } else {
                        MaterialTheme.colors.surface
                    }
                ) {
                    // The Text composable is pre-defined by the Compose UI library; you can use this
                    // composable to render text on the screen
                    Text(text = screen.name, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

/**
 * Returns the corresponding DrawerAppScreen based on the index passed to it.
 */
fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> DrawerAppScreen.Screen1
    1 -> DrawerAppScreen.Screen2
    2 -> DrawerAppScreen.Screen3
    else -> DrawerAppScreen.Screen1
}

/**
 * Passed the corresponding screen composable based on the current screen that's active.
 */
@Composable
fun BodyContentComponent(
    currentScreen: DrawerAppScreen,
    openDrawer: () -> Unit
) {
    when (currentScreen) {
        DrawerAppScreen.Screen1 -> Screen1Component(
            openDrawer
        )
        DrawerAppScreen.Screen2 -> Screen2Component(
            openDrawer
        )
        DrawerAppScreen.Screen3 -> Screen3Component(
            openDrawer
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun Screen1Component(openDrawer: () -> Unit) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 1 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables. Since we want the
        // surface to occupy the entire available width after the TopAppBar has been laid out at
        // the top, we make use of the Modifier.weight modifier and pass the weight  as 1 (which
        // represents entire available weight).

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to.
        Surface(color = Color(0xFFffd7d7.toInt()), modifier = Modifier.weight(1f)) {
            // Center is a composable that centers all the child composables that are passed to it.
            Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center, children = {
                Text(text = "Screen 1")
            })
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun Screen2Component(openDrawer: () -> Unit) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 2 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables. Since we want the
        // surface to occupy the entire available width after the TopAppBar has been laid out at
        // the top, we make use of the Modifier.weight modifier and pass the weight  as 1 (which
        // represents entire available weight).

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to.
        Surface(color = Color(0xFFffe9d6.toInt()), modifier = Modifier.weight(1f)) {
            // Center is a composable that centers all the child composables that are passed to it.
            Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center, children = {
                Text(text = "Screen 2")
            })
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun Screen3Component(openDrawer: () -> Unit) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 3 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables. Since we want the
        // surface to occupy the entire available width after the TopAppBar has been laid out at
        // the top, we make use of the Modifier.weight modifier and pass the weight  as 1 (which
        // represents entire available weight).

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to.
        Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
            // Center is a composable that centers all the child composables that are passed to it.
            Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center, children = {
                Text(text = "Screen 3")
            })
        }
    }
}

enum class DrawerAppScreen {
    Screen1,
    Screen2,
    Screen3
}

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Preview
@Composable
fun DrawerAppComponentPreview() {
    DrawerAppComponent()
}