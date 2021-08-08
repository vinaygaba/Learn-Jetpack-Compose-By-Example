package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MeasuringScaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            CustomTheme {
                MeasuringScaleComponent()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun MeasuringScaleComponent() {
    // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
    // This allows us to also control the scroll position and other scroll related properties.

    // remember calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. More details are available in the
    // comments below.
    val scrollState = rememberScrollState()

    // Row is a composable that places its children in a horizontal sequence. You
    // can think of it similar to a LinearLayout with the horizontal orientation.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we assign a
    // padding of 16dp and specify it to occupy the entire available width.

    // In addition, we make use of the horizontalScroll modifier. This modifier makes the using
    // composable to have scroll functionality in the horizontal direction.
    Row(modifier = Modifier.horizontalScroll(scrollState).padding(top = 16.dp).fillMaxWidth(),
        content = {
            for (i in -20..1020) {
                ScaleLineComponent(i)
            }
        })
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    // In addition we also pass a few modifiers to it.
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
    ) {
        ScaleCenterPointer()
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ScaleLineComponent(index: Int) {
    val isDivisibleBy10 = index % 10 == 0
    // Surface color from the color palette specified by the applied Theme. In our case, its 
    // what we specify in the CustomTheme composable. 
    val surfaceColor = MaterialTheme.colors.surface
    // The color configured for rendering content on top of surfaces that use the surface color.
    val onSurfaceColor = MaterialTheme.colors.onSurface
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. We also give it a
    // modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we add background 
    // color to the Column using the drawBackground(color) modifier.
    Column(modifier = Modifier.background(color = surfaceColor)) {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.
        Canvas(
            modifier = Modifier.padding(5.dp).height(100.dp).width(3.dp)
        ) {
            // Allows you to draw a line between two points (p1 & p2) on the canvas.
            drawLine(
                color = onSurfaceColor,
                start = Offset(0f, 0f),
                end = Offset(0f, if (isDivisibleBy10) size.height else size.height * 0.2f),
                strokeWidth = if (isDivisibleBy10) size.width else size.width * 0.3f
            )
        }
        // Text is a predefined composable that does exactly what you'd expect it to - display text 
        // on the screen. It allows you to customize its appearance using style, fontWeight, 
        // fontSize, etc.
        Text(
            // Adding an empty string to ensure that that it also gets the background color 
            // assigned otherwise it results in bad looking UI. 
            text = if (isDivisibleBy10) "${index / 10}" else "",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            color = onSurfaceColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ScaleCenterPointer() {
    // Primary color from the color palette specified by the applied Theme. In our case, its
    // what we specify in the CustomTheme composable. 
    val primaryColor = MaterialTheme.colors.primary
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    Column {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. In this example, we give it a 
        // padding of 5 dp, height of 120dp & width of 3dp. 
        Canvas(
            modifier = Modifier.padding(5.dp).height(120.dp).width(3.dp)
        ) {
            // Allows you to draw a line between two points (p1 & p2) on the canvas.
            drawLine(
                color = primaryColor,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = size.width
            )
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.

// Below we have created a composable called CustomTheme that in turn accepts Composable 
// functions as a parameter. This is simply a high order function. 
@Composable
fun CustomTheme(children: @Composable() () -> Unit) {
    // lightColors is a default implementation of the ColorPalette from the MaterialDesign
    // specification https://material.io/design/color/the-color-system.html#color-theme-creation.
    // for easy use. In this case, I'm just using the default values and not overriding any 
    // properties like primaryColor, secondaryColor, etc. 
    val lightColors = lightColors()
    // darkColors is a default implementation of dark mode ColorPalette from the
    // Material Design specification
    // https://material.io/design/color/the-color-system.html#color-theme-creation.
    val darkColors = darkColors()
    // We check if the device has activated dark mode and based on this state we pass the 
    // appropriate value to the MaterialTheme.
    val color = if (isSystemInDarkTheme()) darkColors else lightColors
    // A MaterialTheme comprises of colors, typography and the child composables that are going
    // to make use of this styling.
    MaterialTheme(colors = color) {
        children()
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
fun MeasuringScaleComponentPreview() {
    CustomTheme {
        MeasuringScaleComponent()
    }
}
