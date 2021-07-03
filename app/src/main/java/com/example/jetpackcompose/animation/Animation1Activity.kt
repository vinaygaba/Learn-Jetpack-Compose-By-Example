package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.activity.compose.setContent
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

/**
 * PropKeys are used in Jetpack Compose animations to hold properties that are going to be
 * updated by the animation transitions. In this example, we use a [FloatPropKey] to hold a float
 * value that represents the value of rotation.
 */
private val rotation = FloatPropKey()

/**
 * Animations in Jetpack Compose use the [Transition] composable. This transition API depends on
 * transitions between states & changing values, similar to how ValueAnimators worked in the older
 * Android UI Toolkit. Transition's do not care about the actual implementation itself, they just
 * allow you to manipulate and transition between different states and also let you specify what
 * the interpolation, duration & behavior of these transitions should be like. Read through the
 * comments below to understand this better.
 */
private val rotationTransitionDefinition = transitionDefinition<String> {
    // We define a transitionDefinition that's meant to be an exhaustive list of all states &
    // state transitions that are a part of your animation. Below, we define two states - state 0
    // & state 360. For each state, we also define the value of the properties when they are in
    // the respective state. For example - for state A, we assign the rotation prop the value 0f
    // and for state B, we assign the rotation prop the value 360f.
    state("A") { this[rotation] = 0f }
    state("B") { this[rotation] = 360f }

    // Here we define the transition spec i.e what action do we need to do as we transition from
    // one state to another. Below, we define a TransitionSpec for the transition
    // state A -> state B.
    transition(fromState = "A", toState = "B") {
        // For the transition from state A -> state B, we assign a AnimationBuilder to the
        // rotation prop where we specify how we want to update the value of the rotation prop
        // between state A & B, what the duration of this animation should be, what kind of
        // interpolator to use for the animation & how many iterations of this animation are needed.
        // Since we want the rotation to be continous, we use the repeatable AnimationBuilder and
        // set the iterations to Infinite.
        rotation using infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = FastOutLinearInEasing
            )
        )
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
        // Transition composable creates a state-based transition using the animation configuration
        // defined in [TransitionDefinition]. In the example below, we use the
        // rotationTransitionDefinition that we discussed above and also specify the initial
        // state of the animation & the state that we intend to transition to. The expectation is
        // that the transitionDefinition allows for the transition from the "initialState" to the
        // "toState".
        val state = transition(
            definition = rotationTransitionDefinition,
            initState = "A",
            toState = "B"
        )
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that are used
        // to modify the composable that its applied to. In this example, we assign a size
        // of 200dp to the Canvas using Modifier.preferredSize(200.dp).
        Canvas(modifier = Modifier.preferredSize(200.dp)) {
            // As the Transition is changing the interpolating the value of your props based
            // on the "from state" and the "to state", you get access to all the values
            // including the intermediate values as they are being updated. We can use the
            // state variable and access the relevant props/properties to update the relevant
            // composables/layouts. Below, we use state[rotation] to get the latest value of
            // rotation (it will be a value between 0 & 360 depending on where it is in the
            // transition) and use it to rotate our canvas.
            rotate(state[rotation]) {
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
