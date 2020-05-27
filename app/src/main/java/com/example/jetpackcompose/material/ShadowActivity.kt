package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.image.TitleComponent

class ShadowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            ShadowComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ShadowComponent() {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize(). In addition, we 
    // specify that the content of this box should be centered.
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        // We used another Box composable to create a container that will have the shadow applied.
        Box(
            // We specify that the box should have round corners with 8.dp as the radius.
            shape = RoundedCornerShape(8.dp),
            // It will occupy the maximum available width
            modifier = Modifier.fillMaxWidth() +
                    // with a height of 250 dp
                    Modifier.preferredHeight(250.dp) +
                    // and a padding of 16 dp
                    Modifier.padding(16.dp) +
                    // In addition, we will also draw a shadow around the Box using the 
                    // drawShadow modifier. Because its a modifier, it can basically be applied 
                    // to any modifier without much hassle. It's that simple! 
                    Modifier.drawShadow(
                        elevation = 3.dp,
                        clipToOutline = true,
                        shape = RoundedCornerShape(8.dp)
                    ),
            gravity = ContentGravity.Center,
            backgroundColor = colors[2]
        ) {
            // TitleComponent is a composable we created in one of the files that merely renders 
            // text on the screen. 
            TitleComponent("This container has a shadow applied to it")
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
fun ShadowComponentPreview() {
    ShadowComponent()
}
