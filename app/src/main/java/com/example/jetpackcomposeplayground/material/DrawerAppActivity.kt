package com.example.jetpackcomposeplayground.material

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
import androidx.ui.layout.*
import androidx.ui.material.DrawerState
import androidx.ui.material.IconButton
import androidx.ui.material.ModalDrawerLayout
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Menu
import androidx.ui.material.surface.Surface
import androidx.ui.unit.dp

class DrawerAppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawerAppComponent()
        }
    }
}

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
    Column(modifier = LayoutHeight.Fill) {
        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen1
            closeDrawer()
        }) {
            Text(text = DrawerAppScreen.Screen1.name, modifier = LayoutPadding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen2
            closeDrawer()
        }) {
            Text(text = DrawerAppScreen.Screen2.name, modifier = LayoutPadding(16.dp))
        }

        Clickable(onClick = {
            currentScreen.value =
                DrawerAppScreen.Screen3
            closeDrawer()
        }) {
            Text(text = DrawerAppScreen.Screen3.name, modifier = LayoutPadding(16.dp))
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
        Surface(color = Color(255, 222, 3), modifier = LayoutFlexible(1f)) {
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
        Surface(color = Color(3, 54, 255), modifier = LayoutFlexible(1f)) {
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
        Surface(color = Color(255, 2, 102), modifier = LayoutFlexible(1f)) {
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