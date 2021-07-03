package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.core.colors

class LayoutModifierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // ScrollableColumn is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            LazyColumn {
                item { SamePaddingComponent() }
                item { CustomPaddingComponent() }
                item { OffsetComponent() }
                item { AspectRatioComponent() }
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SamePaddingComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.
    Surface(color = colors[0]) {
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(
            text = "This text has equal padding of 16dp in all directions",
            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In this example, we assign a padding of
            // 16dp to the Text composable.
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun CustomPaddingComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.
    Surface(color = colors[1]) {
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(
            text = "This text has 32dp start padding, 4dp end padding, 32dp top padding & 0dp " +
                    "bottom padding padding in each direction",
            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In this example, we assign variable
            // padding to the Text composable.
            modifier = Modifier.padding(start = 32.dp, end = 4.dp, top = 32.dp, bottom = 0.dp),
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun OffsetComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we make use of the
    // Modifier.offset modifier that allows the composable to be shifted along the x & y direction
    // by the user specified amount.
    Surface(color = colors[2], modifier = Modifier.offset(x = 8.dp, y = 8.dp)) {
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(
            text = "This text is using an offset of 8 dp instead of padding. Padding also ends up" +
                    " modifying the size of the layout. Using offset instead ensures that the " +
                    "size of the layout retains its size.",
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun AspectRatioComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we make use of the
    // Modifier.aspectRatio modifier that attempts to size a layout to match a specified aspect ratio.
    Surface(
        color = colors[3], modifier = Modifier
            .aspectRatio(16 / 9f)
            .padding(top = 16.dp)
    ) {
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(
            text = "This text is wrapped in a layout that has a fixed aspect ratio of 16/9",
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif),
            modifier = Modifier.padding(16.dp)
        )
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
@Preview("Example with same padding applied to a composable")
@Composable
fun SamePaddingComponentPreview() {
    Column {
        SamePaddingComponent()
    }
}

@Preview("Example with custom padding in each direction applied to a composable")
@Composable
fun CustomPaddingComponentPreview() {
    Column {
        CustomPaddingComponent()
    }
}

@Preview("Example using offsets to position the composable")
@Composable
fun OffsetComponentPreview() {
    Column {
        OffsetComponent()
    }
}

@Preview("Example showing how a fixed aspect ration is applied a composable")
@Composable
fun AspectRatioComponentPreview() {
    Column {
        AspectRatioComponent()
    }
}
