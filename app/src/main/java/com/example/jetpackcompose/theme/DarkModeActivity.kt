package com.example.jetpackcompose.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.DrawerState
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ModalDrawerLayout
import androidx.ui.material.Surface
import androidx.ui.material.Switch
import androidx.ui.material.TopAppBar
import androidx.ui.material.Typography
import androidx.ui.material.darkColorPalette
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Menu
import androidx.ui.material.lightColorPalette
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.text.style.TextIndent
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.LOREM_IPSUM_1
import com.example.jetpackcompose.core.LOREM_IPSUM_2
import com.example.jetpackcompose.core.LOREM_IPSUM_3

class DarkModeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Reacting to state changes is core to how Jetpack Compose works. This state variable
            // is used to control if dark mode is enabled or not. The value is toggled using a
            // button that's part of the ThemedDrawerAppComponent composable. Every time the
            // value of this variable changes, the relevant sub composables of
            // ThemedDrawerAppComponent that use enableDarkMode are automatically recomposed.
            val enableDarkMode = state { false }
            CustomTheme(enableDarkMode) {
                ThemedDrawerAppComponent(enableDarkMode)
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun CustomTheme(enableDarkMode: MutableState<Boolean>, children: @Composable()() -> Unit) {
    // lightColorPalette is a default implementation of the ColorPalette from the MaterialDesign
    // specification https://material.io/design/color/the-color-system.html#color-theme-creation.
    // for easy use. In this case, I'm just showing an example of how you can
    // override any of the values that are a part of the Palette even though I'm just using the
    // default values itself.
    val lightColors = lightColorPalette(
        primary = Color(0xFF6200EE),
        primaryVariant = Color(0xFF3700B3),
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFF03DAC5),
        secondaryVariant = Color(0xFF0000FF),
        onSecondary = Color(0xFF000000),
        background = Color(0xFFFFFFFF),
        onBackground = Color(0xFF000000),
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF000000),
        error = Color(0xFFB00020),
        onError = Color(0xFFFFFFFF)
    )

    // lightColorPalette is a default implementation of dark mode ColorPalette from the
    // Material Design specification
    // https://material.io/design/color/the-color-system.html#color-theme-creation.
    val darkColors = darkColorPalette()
    val colors = if (enableDarkMode.value) darkColors else lightColors

    // Data class holding typography definitions as defined by the
    // Material typography specification
    // https://material.io/design/typography/the-type-system.html#type-scale
    val typography = Typography(
        body1 = TextStyle(fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textIndent = TextIndent(firstLine = TextUnit.Companion.Sp(16)),
            textAlign = TextAlign.Justify
        )
    )

    // A MaterialTheme comprises of colors, typography and the child composables that are going
    // to make use of this styling.
    MaterialTheme(colors = colors, content = children, typography = typography)
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ThemedDrawerAppComponent(enableDarkMode: MutableState<Boolean>) {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the drawerState. Any composable that reads the value of drawerState will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val (drawerState, onDrawerStateChange) = state { DrawerState.Closed }
    // State composable used to hold the value of the current active screen
    val currentScreen = state { ThemedDrawerAppScreen.Screen1 }

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
            ThemedDrawerContentComponent(
                currentScreen = currentScreen,
                closeDrawer = { onDrawerStateChange(DrawerState.Closed) }
            )
        },
        bodyContent = {
            // bodyContent takes a composable to represent the view/layout to display on the
            // screen. We select the appropriate screen based on the value stored in currentScreen.
            ThemedBodyContentComponent(
                currentScreen = currentScreen.value,
                enableDarkMode = enableDarkMode,
                openDrawer = {
                    onDrawerStateChange(DrawerState.Opened)
                }
            )
        }
    )
}

@Composable
fun ThemedDrawerContentComponent(
    currentScreen: MutableState<ThemedDrawerAppScreen>,
    closeDrawer: () -> Unit
) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. In addition, we
    // also provide the column with a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we configure the Column to
    // occupy the entire available height using the Modifier.fillMaxHeight() modifier.

    // In addition, we use the LayoutOffset modifier to take into account the appbar that sits on
    // top of the drawer content. So we add an offset in the y direction. Alternatively, we can
    // use the Scaffold composable that takes care of placing the drawer content in the correct
    // position. Look at [FixedActionButtonActivity] to see an example.
    Column(modifier = Modifier.fillMaxHeight()) {
        // Clickable wraps the child composable and enables it to react to a click through the onClick
        // callback similar to the onClick listener that we are accustomed to on Android.
        // Here, we just update the currentScreen variable to hold the appropriate value based on
        // the row that is clicked i.e if the first row is clicked, we set the value of
        // currentScreen to DrawerAppScreen.Screen1, when second row is clicked we set it to
        // DrawerAppScreen.Screen2 and so on and so forth.
        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen1
            // We also close the drawer when an option from the drawer is selected.
            closeDrawer()
        }) {
            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using
            // the style property.
            Text(text = ThemedDrawerAppScreen.Screen1.name, modifier = Modifier.padding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen2
            closeDrawer()
        }) {
            Text(text = ThemedDrawerAppScreen.Screen2.name, modifier = Modifier.padding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen3
            closeDrawer()
        }) {
            Text(text = ThemedDrawerAppScreen.Screen3.name, modifier = Modifier.padding(16.dp))
        }
    }
}

/**
 * Passed the corresponding screen composable based on the current screen that's active.
 */
@Composable
fun ThemedBodyContentComponent(
    currentScreen: ThemedDrawerAppScreen,
    enableDarkMode: MutableState<Boolean>,
    openDrawer: () -> Unit
) {
    val onCheckChanged = { _: Boolean ->
        enableDarkMode.value = !enableDarkMode.value
    }
    when (currentScreen) {
        ThemedDrawerAppScreen.Screen1 -> ThemedScreen1Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen2 -> ThemedScreen2Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen3 -> ThemedScreen3Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ThemedScreen1Component(
    enableDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. In addition, we
    // also provide the column with a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we configure the Column to
    // occupy the entire available width & height using the Modifier.fillMaxSize() modifier.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 1") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply a modifier.
        Card(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface
        ) {
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            Row(modifier = Modifier.padding(16.dp)) {
                // A pre-defined composable that's capable of rendering a switch. It honors the Material
                // Design specification.
                Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
                Text(
                    text = "Enable Dark Mode",
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables.
        Surface(modifier = Modifier.weight(1f), color = MaterialTheme.colors.surface) {
            Text(
                text = LOREM_IPSUM_1,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ThemedScreen2Component(
    enableDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. In addition, we
    // also provide the column with a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we configure the Column to
    // occupy the entire available width & height using the Modifier.fillMaxSize() modifier.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 2") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply a modifier.
        Card(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface
        ) {
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            Row(modifier = Modifier.padding(16.dp)) {
                // A pre-defined composable that's capable of rendering a switch. It honors the Material
                // Design specification.
                Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
                Text(text = "Enable Dark Mode", style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables.
        Surface(modifier = Modifier.weight(1f)) {
            Text(text = LOREM_IPSUM_2, style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ThemedScreen3Component(
    enableDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. In addition, we
    // also provide the column with a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we configure the Column to
    // occupy the entire available width & height using the Modifier.fillMaxSize() modifier.
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar is a pre-defined composable that's placed at the top of the screen. It has
        // slots for a title, navigation icon, and actions. Also known as the action bar.
        TopAppBar(
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            title = { Text("Screen 3") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(asset = Icons.Filled.Menu)
                }
            }
        )
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply a modifier.
        Card(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.surface
        ) {
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            Row(modifier = Modifier.padding(16.dp)) {
                // A pre-defined composable that's capable of rendering a switch. It honors the Material
                // Design specification.
                Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
                Text(text = "Enable Dark Mode", style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables.
        Surface(modifier = Modifier.weight(1f)) {
            Text(text = LOREM_IPSUM_3, style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

enum class ThemedDrawerAppScreen {
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
fun CustomThemeLightPreview() {
    CustomTheme(enableDarkMode = state { false }) {
        Card {
            Text("Preview Text", modifier = Modifier.padding(32.dp))
        }
    }
}

@Preview
@Composable
fun CustomThemeDarkPreview() { 
    CustomTheme(enableDarkMode = state { true }) {
        Card {
            Text("Preview Text", modifier = Modifier.padding(32.dp))
        }
    }
}
