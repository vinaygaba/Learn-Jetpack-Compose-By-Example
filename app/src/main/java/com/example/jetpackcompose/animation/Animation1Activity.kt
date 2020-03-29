package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.animation.FastOutLinearInEasing
import androidx.animation.FloatPropKey
import androidx.animation.Infinite
import androidx.animation.transitionDefinition
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Transition
import androidx.ui.core.setContent
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.layout.Center
import androidx.ui.layout.LayoutSize
import androidx.ui.unit.dp
import androidx.ui.unit.toRect

class Animation1Activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            RotatingSquareComponent()
        }
    }
}

private val rotation = FloatPropKey()
private val rotationTransitionDefinition = transitionDefinition {
    state(0){ this[rotation] = 0f }
    state(360) { this[rotation] = 360f }

    transition(0 to 360) {
        rotation using repeatable {
            animation = tween {
                duration = 3000
                easing = FastOutLinearInEasing
            }
            iterations = Infinite
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun RotatingSquareComponent() {
    Center {
        Transition(
            definition = rotationTransitionDefinition,
            initState = 0,
            toState = 360
        ) { state ->
            Canvas(modifier = LayoutSize(200.dp)) {
                save()
                translate(size.width.value/2, size.height.value/2)
                rotate(state[rotation])
                translate(-size.width.value/2, -size.height.value/2)
                drawRect(size.toRect(), Paint().apply { color = Color(255, 138, 128) })
                restore()
            }
        }
    }
}