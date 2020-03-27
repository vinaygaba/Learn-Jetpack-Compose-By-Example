package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.animation.LinearEasing
import androidx.animation.transitionDefinition
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.Transition
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutSize

class Animation2Activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            AnimateColorComponent()
        }
    }
}

private val color = ColorPropKey()

private val colorDefinition = transitionDefinition {
    state(0) {
        this[color] = Color.Red
    }

    state(1) {
        this[color] = Color.Green
    }

    state(2) {
        this[color] = Color.Blue
    }

    transition(0 to 1, 1 to 2, 2 to 0) {
        color using tween {
            duration = 2000
            easing = LinearEasing
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun AnimateColorComponent() {
    var initialState by state { 0 }
    var toState by state { 1 }
    Transition(definition = colorDefinition, initState = initialState, toState = toState,
        onStateChangeFinished =
        { state ->
            when (state) {
                0 -> {
                    initialState = 0
                    toState = 1
                }
                1 -> {
                    initialState = 1
                    toState = 2
                }
                2 -> {
                    initialState = 2
                    toState = 0
                }
            }
        }) {
        Box(LayoutSize.Fill, backgroundColor = it[color])
    }
}