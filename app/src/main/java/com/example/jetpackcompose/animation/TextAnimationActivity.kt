package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

class TextAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            TextAnimationComponent()
        }
    }
}

/**
 * PropKeys are used in Jetpack Compose animations to hold properties that are going to be
 * updated by the animation transitions. In this example, we use a [ColorPropKey] to hold a Color
 * value that represents the value of color on screen & a [FloatPropKey] to hold a float value to
 * represent the rotation of the compose icon.
 */
private val color = ColorPropKey()
private val rotation = FloatPropKey()

/**
 * Animations in Jetpack Compose use the [Transition] composable. This transition API depends on
 * transitions between states & changing values, similar to how ValueAnimators worked in the older
 * Android UI Toolkit. Transition's do not care about the actual implementation itself, they just
 * allow you to manipulate and transition between different states and also let you specify what
 * the interpolation, duration & behavior of these transitions should be like. Read through the
 * comments below to understand this better.
 */
private val colorDefinition = transitionDefinition<Int> {
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
        color using tween<Color>(
            durationMillis = 200,
            easing = LinearEasing
        )
    }
}

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
        rotation using repeatable<Float>(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = FastOutLinearInEasing
            ),
            iterations = Infinite
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun TextAnimationComponent() {
    // Annotate string is used to define a text with multiple styles.
    val text = annotatedString {
        // appended string
        append("Jetpack ")
        // Add inline content. Inline content is used to describe/tag sections inside the Text 
        // composable that we will replace by other composables tagged with the same id. For 
        // example, we add two sections with id's "composeLogo" & "animatedText" below. We will 
        // reference the same id's in the inlineContent map that we will be passing to the Text 
        // Commposable.
        appendInlineContent("composeLogo", "Compose Logo")
        appendInlineContent("animatedText", "Animated Text")
    }

    val inlineContent = mapOf(
        "composeLogo" to InlineTextContent(
            placeholder = Placeholder(
                width = 2.em,
                height = 1.em,
                placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
            ),
            children = {
                ComposeLogoComponent()
            }
        ),
        "animatedText" to InlineTextContent(
            placeholder = Placeholder(
                width = 5.em,
                height = 35.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
            ),
            children = {
                ColorChangingTextComponent()
            }
        )
    )
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the
    // Box to occupy the entire available height & width using the Modifier.fillMaxSize() modifier.
    Column(
        modifier = Modifier.fillMaxSize(), 
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text is a predefined composable that does exactly what you'd expect it to - display text 
        // on the screen. It allows you to customize its appearance using style, fontWeight, 
        // fontSize, etc.

        // In addition, we also pass it the inlineContent map that is used to describe how we 
        // will specify alternate composables to describe areas within the Text composable.
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 35.sp
            ),
            inlineContent = inlineContent
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ComposeLogoComponent() {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the loadImageResource method as it loads an image resource asynchronously
    val image = loadImageResource(R.drawable.compose_logo)
    image.resource.resource?.let { img ->
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

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. In this example, we configure the
        // Image composable to have a height of 48 dp.
        Canvas(Modifier.preferredSize(48.dp)) {
            // As the Transition is changing the interpolating the value of your props based
            // on the "from state" and the "to state", you get access to all the values
            // including the intermediate values as they are being updated. We can use the
            // state variable and access the relevant props/properties to update the relevant
            // composables/layouts. Below, we use state[rotation] to get the latest value of
            // rotation (it will be a value between 0 & 360 depending on where it is in the
            // transition) and use it to rotate our canvas.
            rotate(state[rotation]) {
                drawImage(img)
            }
        }
        transition(
            definition = rotationTransitionDefinition,
            toState = "B",
            initState = "A"
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ColorChangingTextComponent() {
    var initialState by remember { mutableStateOf(0) }
    var toState by remember { mutableStateOf(1) }
    // Transition composable creates a state-based transition using the animation configuration
    // defined in [TransitionDefinition]. In the example below, we use the
    // colorDefinition that we discussed above and also specify the initial
    // state of the animation & the state that we intend to transition to. The expectation is
    // that the transitionDefinition has been configured to allow for the transition from the
    // "initialState" to the "toState".
    val state = transition(
        definition = colorDefinition,
        initState = initialState,
        toState = toState,
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
        })

    // As the Transition is changing the interpolating the value of your props based
    // on the "from state" and the "to state", you get access to all the values
    // including the intermediate values as they are being updated. We can use the
    // state variable and access the relevant props/properties to update the relevant
    // composables/layouts. Below, we use state[color] to get get the latest value of color
    // and use it to set the color of the Text composable.
    Text(
        text = "Compose",
        color = state[color],
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 35.sp
        )
    )
}
