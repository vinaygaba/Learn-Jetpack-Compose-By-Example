package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Animation1Activity : AppCompatActivity() {
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

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun RotatingSquareComponent() {
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize().
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            // rememberInfiniteTransition is used to create a transition that uses infitine
            // child animations. Animations typically get invoked as soon as they enter the
            // composition so don't need to be explicitly started.
            val infiniteTransition = rememberInfiniteTransition()

            // Create a value that is altered by the transition based on the configuration. We use
            // the animated float value the returns and updates a float from the initial value to
            // target value and repeats it (as its called on the infititeTransition).
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween<Float>(
                        durationMillis = 3000,
                        easing = FastOutLinearInEasing,
                    ),
                )
            )
            // We use the Canvas composable that gives you access to a canvas that you can draw
            // into. We also pass it a modifier.

            // You can think of Modifiers as implementations of the decorators pattern that are used
            // to modify the composable that its applied to. In this example, we assign a size
            // of 200dp to the Canvas using Modifier.preferredSize(200.dp).
            Canvas(modifier = Modifier.size(200.dp)) {
                // As the Transition is changing the interpolating the value of the animated float
                // "rotation", you get access to all the values including the intermediate values as
                // its  being updated. The value of "rotation" goes from 0 to 360 and transitions
                // infinitely due to the infiniteRepetable animationSpec used above.
                rotate(rotation) {
                    drawRect(color = Color(255, 138, 128))
                }
            }
        })
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
fun RotatingSquareComponentPreview() {
    RotatingSquareComponent()
}
