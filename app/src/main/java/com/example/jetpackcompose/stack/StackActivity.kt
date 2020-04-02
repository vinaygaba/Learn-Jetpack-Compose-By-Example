package com.example.jetpackcompose.stack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.image.LocalResourceImageComponent

class StackActivity: AppCompatActivity() {
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
    // Stack is a predefined component that is useful for drawing children that overlap. The
    // children will always be drawn in the order they are specified in the body of the Stack. It
    // works similar to the FrameLayout that we are used to using in the original UI toolkit.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we assign a
    // ask the Stack to occupy the full available width using the LayoutWidth.Fill modifier and
    // give it a height of 200 dp.
    Stack(modifier = LayoutWidth.Fill + LayoutHeight(200.dp)) {
        // LocalResourceImageComponent is a composable that takes in an image resource id and
        // display it on the screen. Take a look at its implementation to learn more.
        LocalResourceImageComponent(resId = R.drawable.lenna)
        // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
        // Material Design specification. It's generally used to change the background color, add
        // elevation, clip or add background shape to its children composables.
        Surface(color = Color.Gray) {
            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.
            Text("Title",
                style = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
                    fontSize = 14.sp),
                modifier = LayoutPadding(16.dp) + LayoutWidth.Fill
            )
        }
    }
}