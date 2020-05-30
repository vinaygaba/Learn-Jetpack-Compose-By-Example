package com.example.jetpackcompose.interop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.compose.Composable
import androidx.compose.Recomposer
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.core.colors

class ComposeInLegacyAndroidActivity : AppCompatActivity() {
    private lateinit var containerLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_in_legacy_android)
        containerLayout = findViewById(R.id.frame_container)
        // We make use of the setContent extension function that's available for all view groups.
        // This allows us to render a @Composable function inside a view group. This allows us to
        // render composables inside legacy views. In the example below, we use the frame layout 
        // called containerLayout and pass the composable called CardComponentWithMessage to 
        // render inside it.
        containerLayout.setContent(Recomposer.current()) {
            CardComponentWithMessage()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun CardComponentWithMessage() {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to occupuy the entire available width and height using
    // Modifier.fillMaxSize() and give center gravity to the content inside this box.
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = ContentGravity.Center
    ) {
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply a modifier.
        Card(
            modifier = Modifier.fillMaxWidth() +
                    Modifier.preferredHeight(200.dp) +
                    Modifier.padding(16.dp),
            color = colors[1],
        ) {
            // Text is a predefined composable that does exactly what you'd expect it to - display text on
            // the screen. It allows you to customize its appearance using style, fontWeight, fontSize, etc.
            Text("This is an example of a Jetpack Compose composable inside a legacy Android view", 
                style = TextStyle(
                    fontFamily = FontFamily.Monospace, 
                    fontWeight = FontWeight.W900, 
                    fontSize = 14.sp, 
                    color = Color.Black), 
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
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
@Composable
fun CardComponentWithMessagePreview() {
    CardComponentWithMessage()
}
