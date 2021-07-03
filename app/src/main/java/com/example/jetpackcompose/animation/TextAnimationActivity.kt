package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
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

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun TextAnimationComponent() {
    // Annotate string is used to define a text with multiple styles.
    val text = buildAnnotatedString {
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
    // be advisable to use the painterResource method as it loads an image resource asynchronously
    val image = ImageBitmap.imageResource(R.drawable.compose_logo)

    // TODO: Add comment
    val infiniteTransition = rememberInfiniteTransition()
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

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Image composable to have a height of 48 dp.
    Canvas(Modifier.size(48.dp)) {
        // As the Transition is changing the interpolating the value of your props based
        // on the "from state" and the "to state", you get access to all the values
        // including the intermediate values as they are being updated. We can use the
        // state variable and access the relevant props/properties to update the relevant
        // composables/layouts. Below, we use state[rotation] to get the latest value of
        // rotation (it will be a value between 0 & 360 depending on where it is in the
        // transition) and use it to rotate our canvas.
        rotate(rotation) {
            drawImage(image)
        }
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ColorChangingTextComponent() {
    // TODO: Add comment
    val currentColor by remember { mutableStateOf(Color.Red) }
    val transition = updateTransition(currentColor)

    val color by transition.animateColor { state ->
        when (state) {
            Color.Red -> Color.Green
            Color.Green -> Color.Blue
            Color.Blue -> Color.Red
            else -> Color.Red
        }
    }

    // As the Transition is changing the interpolating the value of your props based
    // on the "from state" and the "to state", you get access to all the values
    // including the intermediate values as they are being updated. We can use the
    // state variable and access the relevant props/properties to update the relevant
    // composables/layouts. Below, we use state[color] to get get the latest value of color
    // and use it to set the color of the Text composable.
    Text(
        text = "Compose",
        color = color,
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 35.sp
        )
    )
}
