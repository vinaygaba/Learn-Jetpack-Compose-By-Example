package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.geometry.Offset
import androidx.ui.graphics.drawscope.Stroke
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MeasuringScaleActivity: AppCompatActivity() {
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
    // HorizontalScroller is a composable that adds the ability to scroll through the child
    // composables that are declared inside it in the horizontal direction. One caveat here is that
    // this is not optimized to recycle the views. It is more similar to [ScrollView] and should not
    // be thought of as a replacement for [RecyclerView]. We also give it a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we assign a
    // padding of 16dp to the HorizontalScroller and specify it to occupy the entire available 
    // width.
    HorizontalScroller(
        modifier = Modifier.padding(top = 16.dp) + Modifier.fillMaxWidth()
    ) {
        for (i in -20..1020) {
            ScaleLineComponent(i)
        }
    }
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. We give it a ContentGravity of Center to ensure the children of this composable
    // are placed in its center. In addition we also pass a few modifiers to it.
    Box(
        gravity = ContentGravity.Center,
        modifier = Modifier.padding(top = 16.dp) + Modifier.fillMaxWidth()
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
    Column(modifier = Modifier.drawBackground(surfaceColor)) {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.
        Canvas(
            modifier = Modifier.padding(5.dp) + Modifier.preferredHeight(100.dp) + Modifier
                .preferredWidth(3.dp)
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
            text = if (isDivisibleBy10) "${index/10}" else "",
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
            modifier = Modifier.padding(5.dp) + Modifier.preferredHeight(120.dp) + Modifier
                .preferredWidth(3.dp)
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
fun CustomTheme(children: @Composable()() -> Unit) {
    // lightColorPalette is a default implementation of the ColorPalette from the MaterialDesign
    // specification https://material.io/design/color/the-color-system.html#color-theme-creation.
    // for easy use. In this case, I'm just using the default values and not overriding any 
    // properties like primaryColor, secondaryColor, etc. 
    val lightColors = lightColorPalette()
    // darkColorPalette is a default implementation of dark mode ColorPalette from the
    // Material Design specification
    // https://material.io/design/color/the-color-system.html#color-theme-creation.
    val darkColors = darkColorPalette()
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
