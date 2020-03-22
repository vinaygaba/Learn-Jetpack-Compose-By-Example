package com.example.jetpackcompose.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
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
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.LOREM_IPSUM_1
import com.example.jetpackcompose.core.LOREM_IPSUM_2
import com.example.jetpackcompose.core.LOREM_IPSUM_3

class DarkModeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val enableDarkMode = state { false }
            CustomTheme(enableDarkMode) {
                ThemedDrawerAppComponent(enableDarkMode)
            }
        }
    }
}

@Composable
fun CustomTheme(overrideDarkMode: MutableState<Boolean>, children: @Composable()() -> Unit) {
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
    val darkColors = darkColorPalette()
    val colors = if (overrideDarkMode.value) darkColors else lightColors

    val typography = Typography(
        body1 = TextStyle(fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textIndent = TextIndent(firstLine = TextUnit.Companion.Sp(16)),
            textAlign = TextAlign.Justify
        )
    )

    MaterialTheme(colors = colors, children = children, typography = typography)
}

@Composable
fun ThemedDrawerAppComponent(overrideDarkMode: MutableState<Boolean>) {
    val (drawerState, onDrawerStateChange) = state { DrawerState.Closed }
    val currentScreen = state { ThemedDrawerAppScreen.Screen1 }
    ModalDrawerLayout(
        drawerState = drawerState,
        onStateChange = onDrawerStateChange,
        gesturesEnabled = drawerState == DrawerState.Opened,
        drawerContent = {
            ThemedDrawerContentComponent(
                currentScreen = currentScreen,
                closeDrawer = { onDrawerStateChange(DrawerState.Closed) }
            )
        },
        bodyContent = {
            ThemedBodyContentComponent(
                currentScreen = currentScreen.value,
                overrideDarkMode = overrideDarkMode,
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
    Column(modifier = LayoutHeight.Fill) {
        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen1
            closeDrawer()
        }) {
            Text(text = ThemedDrawerAppScreen.Screen1.name, modifier = LayoutPadding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen2
            closeDrawer()
        }) {
            Text(text = ThemedDrawerAppScreen.Screen2.name, modifier = LayoutPadding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value = ThemedDrawerAppScreen.Screen3
            closeDrawer()
        }) {
            Text(text = ThemedDrawerAppScreen.Screen3.name, modifier = LayoutPadding(16.dp))
        }
    }
}

@Composable
fun ThemedBodyContentComponent(
    currentScreen: ThemedDrawerAppScreen,
    overrideDarkMode: MutableState<Boolean>,
    openDrawer: () -> Unit
) {
    val onCheckChanged = { _: Boolean ->
        overrideDarkMode.value = !overrideDarkMode.value
    }
    when (currentScreen) {
        ThemedDrawerAppScreen.Screen1 -> ThemedScreen1Component(
            overrideDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen2 -> ThemedScreen2Component(
            overrideDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen3 -> ThemedScreen3Component(
            overrideDarkMode.value,
            openDrawer,
            onCheckChanged
        )
    }
}

@Composable
fun ThemedScreen1Component(
    overrideDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 1") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Card(
            modifier = LayoutWidth.Fill,
            color = MaterialTheme.colors().surface
        ) {
            Row(modifier = LayoutPadding(16.dp)) {
                Switch(checked = overrideDarkMode, onCheckedChange = onCheckChanged)
                Text(
                    text = "Enable Dark Mode", style = MaterialTheme.typography().body1,
                    modifier = LayoutPadding(start = 8.dp)
                )
            }
        }
        Surface(modifier = LayoutWeight(1f)) {
            Text(
                text = LOREM_IPSUM_1, style = MaterialTheme.typography().body1,
                modifier = LayoutPadding(16.dp)
            )
        }
    }
}

@Composable
fun ThemedScreen2Component(
    overrideDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 2") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Card(
            modifier = LayoutWidth.Fill,
            color = MaterialTheme.colors().surface
        ) {
            Row(modifier = LayoutPadding(16.dp)) {
                Switch(checked = overrideDarkMode, onCheckedChange = onCheckChanged)
                Text(text = "Enable Dark Mode", style = MaterialTheme.typography().body1,
                    modifier = LayoutPadding(start = 8.dp))
            }
        }
        Surface(modifier = LayoutWeight(1f)) {
            Text(text = LOREM_IPSUM_2, style = MaterialTheme.typography().body1,
                modifier = LayoutPadding(16.dp)
            )
        }
    }
}

@Composable
fun ThemedScreen3Component(
    overrideDarkMode: Boolean,
    openDrawer: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 3") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Card(
            modifier = LayoutWidth.Fill,
            color = MaterialTheme.colors().surface
        ) {
            Row(modifier = LayoutPadding(16.dp)) {
                Switch(checked = overrideDarkMode, onCheckedChange = onCheckChanged)
                Text(text = "Enable Dark Mode", style = MaterialTheme.typography().body1,
                    modifier = LayoutPadding(start = 8.dp))
            }
        }
        Surface(modifier = LayoutWeight(1f)) {
            Text(text = LOREM_IPSUM_3, style = MaterialTheme.typography().body1,
                modifier = LayoutPadding(16.dp)
            )
        }
    }
}

enum class ThemedDrawerAppScreen {
    Screen1,
    Screen2,
    Screen3
}