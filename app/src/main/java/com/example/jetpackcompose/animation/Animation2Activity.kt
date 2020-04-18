package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.animation.LinearEasing
import androidx.animation.transitionDefinition
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.Transition
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.tooling.preview.Preview

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

/**
 * PropKeys are used in Jetpack Compose animations to hold properties that are going to be
 * updated by the animation transitions. In this example, we use a [ColorPropKey] to hold a Color
 * value that represents the value of color on screen.
 */
private val color = ColorPropKey()

/**
 * Animations in Jetpack Compose use the [Transition] composable. This transition API depends on
 * transitions between states & changing values, similar to how ValueAnimators worked in the older
 * Android UI Toolkit. Transition's do not care about the actual implementation itself, they just
 * allow you to manipulate and transition between different states and also let you specify what
 * the interpolation, duration & behavior of these transitions should be like. Read through the
 * comments below to understand this better.
 */
private val colorDefinition = transitionDefinition {
    // We define a transitionDefinition that's meant to be an exhaustive list of all states &
    // state transitions that are a part of your animation. Below, we define three states - state 0
    // state 1, & state 2. For each state, we also define the value of the properties when they
    // are in the respective state. For example - for state 1, we assign the color prop to have
    // the value Color.RED, for state 2 the value of color is Color.Green & for state 3 the value
    // of color prop is Color.Blue.
    state(0) {
        this[color] = Color.Red
    }

    state(1) {
        this[color] = Color.Green
    }

    state(2) {
        this[color] = Color.Blue
    }

    // Here we define the transition spec i.e what action do we need to do as we transition from
    // one state to another. Below, we define a TransitionSpec for the transition from
    // state 0 -> state1, state 1 -> state 2 & state 2 -> state 0.
    transition(0 to 1, 1 to 2, 2 to 0) {
        // We are using the tween animation and have also specified the duration of this
        // animation(2000ms). By using ColorPropKey and tween animation, as we transition from
        // state 0 (Color.RED) to state 1 (Color.Green), the value of color automatically updates
        // to represent Color.Red at the start of the transition and Color.Green at the end of
        // transition. In addition, we also get all the intermediate values of colors between those
        // two colors automatically. This allows us to show a seamless transitions as the colors
        // change.
        color using tween {
            duration = 2000
            easing = LinearEasing
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun AnimateColorComponent() {
    var initialState by state { 0 }
    var toState by state { 1 }
    // Transition composable creates a state-based transition using the animation configuration
    // defined in [TransitionDefinition]. In the example below, we use the
    // colorDefinition that we discussed above and also specify the initial
    // state of the animation & the state that we intend to transition to. The expectation is
    // that the transitionDefinition has been configured to allow for the transition from the
    // "initialState" to the "toState".
    Transition(definition = colorDefinition, initState = initialState, toState = toState,
        onStateChangeFinished =
        // Here we define the action we want to do every time a state transition completes. In
        // our example, we want to continue looping from state 0 -> state 1, state 1 -> state 2,
        // state 2 -> state 0 and keep repeating this over and over. In order to accomplish this,
        // we need to modify the initialState & the toState accordingly. By using the state
        // delegate, we can easily do this as changes to the initialState & toState variables
        // also causes the composables to be redrawn.
        { state ->
            when (state) {
                // When state is 0, set initialState to 0 & toState to 1
                0 -> {
                    initialState = 0
                    toState = 1
                }
                // When state is 1, set initialState to 1 & toState to 2
                1 -> {
                    initialState = 1
                    toState = 2
                }
                // When state is 2, set initialState to 2 & toState to 0
                2 -> {
                    initialState = 2
                    toState = 0
                }
            }
        }) { state ->
        // As the Transition is changing the interpolating the value of your props based
        // on the "from state" and the "to state", you get access to all the values
        // including the intermediate values as they are being updated. We can use the
        // state variable and access the relevant props/properties to update the relevant
        // composables/layouts. Below, we use state[color] to get get the latest value of color
        // and use it to paint the screen by setting it as the backgroundColor of the screen.
        Box(modifier = Modifier.fillMaxSize(), backgroundColor = state[color])
    }
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
fun AnimateColorComponentPreview() {
    AnimateColorComponent()
}
