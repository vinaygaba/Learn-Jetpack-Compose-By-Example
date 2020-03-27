package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.DrawerState
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ModalDrawerLayout
import androidx.ui.material.Surface
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Menu
import androidx.ui.text.TextStyle
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
// functions can only be called from within the scope of other composable functions.
@Composable
fun DrawerAppComponent() {
    val (drawerState, onDrawerStateChange) = state { DrawerState.Closed }
    val currentScreen = state { DrawerAppScreen.Screen1 }
    ModalDrawerLayout(
        drawerState = drawerState,
        onStateChange = onDrawerStateChange,
        gesturesEnabled = drawerState == DrawerState.Opened,
        drawerContent = {
            DrawerContentComponent(
                currentScreen = currentScreen,
                closeDrawer = { onDrawerStateChange(DrawerState.Closed) }
            )
        },
        bodyContent = {
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
    // Drawer content isn't taking into account the appbar so the first row was getting hidden
    // behind the app bar in 0.1.0-dev07. So need to add an offset in the y direction.
    Column(modifier = LayoutSize.Fill + LayoutOffset(y = 56.dp, x = 0.dp)) {
        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen1
            closeDrawer()
        }) {
            Surface(modifier = LayoutWidth.Fill, color = if (currentScreen.value == DrawerAppScreen.Screen1) {
                MaterialTheme.colors().secondary
            } else {
                MaterialTheme.colors().surface
            }) {
                Text(text = DrawerAppScreen.Screen1.name, modifier = LayoutPadding(16.dp))
            }
        }

        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen2
            closeDrawer()
        }) {
            Surface(modifier = LayoutWidth.Fill, color = if (currentScreen.value ==
                DrawerAppScreen.Screen2) {
                MaterialTheme.colors().secondary
            } else {
                MaterialTheme.colors().surface
            }) {
                Text(text = DrawerAppScreen.Screen2.name, modifier = LayoutPadding(16.dp))
            }
        }

        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen3
            closeDrawer()
        }) {
            Surface(modifier = LayoutWidth.Fill, color = if (currentScreen.value ==
                DrawerAppScreen.Screen3) {
                MaterialTheme.colors().secondary
            } else {
                MaterialTheme.colors().surface
            }) {
                Text(text = DrawerAppScreen.Screen3.name, modifier = LayoutPadding(16.dp))
            }
        }
    }
}

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

@Composable
fun Screen1Component(openDrawer: () -> Unit) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 1 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Surface(color = Color(0xFFffd7d7.toInt()), modifier = LayoutWeight(1f)) {
            Center {
                Text(text = "Screen 1")
            }
        }
    }
}

@Composable
fun Screen2Component(openDrawer: () -> Unit) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 2 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Surface(color = Color(0xFFffe9d6.toInt()), modifier = LayoutWeight(1f)) {
            Center {
                Text(text = "Screen 2")
            }
        }
    }
}

@Composable
fun Screen3Component(openDrawer: () -> Unit) {
    Column(modifier = LayoutSize.Fill) {
        TopAppBar(
            title = { Text("Screen 3 Title") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(icon = Icons.Filled.Menu)
                }
            }
        )
        Surface(color = Color(0xFFfffbd0.toInt()), modifier = LayoutWeight(1f)) {
            Center {
                Text(text = "Screen 3")
            }
        }
    }
}

enum class DrawerAppScreen {
    Screen1,
    Screen2,
    Screen3
}