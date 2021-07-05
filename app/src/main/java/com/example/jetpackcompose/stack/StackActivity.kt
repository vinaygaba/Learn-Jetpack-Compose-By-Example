package com.example.jetpackcompose.stack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.R
import com.example.jetpackcompose.image.LocalResourceImageComponent

class StackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            StackComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun StackComponent() {
    // Box is a predefined component that is useful for drawing children that overlap. The
    // children will always be drawn in the order they are specified in the body of the Box. It
    // works similar to the FrameLayout that we are used to using in the original UI toolkit.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we assign a
    // ask the Box to occupy the full available width using the Modifier.fillMaxWidth() modifier
    // and give it a height of 200 dp.
    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        // LocalResourceImageComponent is a composable that takes in an image resource id and
        // display it on the screen. Take a look at its implementation to learn more.
        LocalResourceImageComponent(resId = R.drawable.landscape)
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables.
        Surface(color = Color.Gray) {
            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.
            Text(
                "Title",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
        }
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
fun StackComponentPreview() {
    StackComponent()
}
