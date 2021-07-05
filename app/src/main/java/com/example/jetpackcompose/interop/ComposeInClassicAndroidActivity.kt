package com.example.jetpackcompose.interop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.core.colors

class ComposeInClassicAndroidActivity : AppCompatActivity() {
    private lateinit var containerLayout: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_in_classic_android)
        containerLayout = findViewById(R.id.compose_view)
        // We make use of the setContent extension function that's available on the ComposeView 
        // view that is capable of hosting a @Composable function inside it. This allows us to 
        // render composables inside classic android views.
        containerLayout.setContent {
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
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Column composable to occupuy the entire available width and height using
    // Modifier.fillMaxSize() and center the content inside the Column using the appropriate 
    // veritical arrangement & horizontal alignment.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply a modifier.
        Card(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            backgroundColor = colors[1],
        ) {
            // Text is a predefined composable that does exactly what you'd expect it to - display text on
            // the screen. It allows you to customize its appearance using style, fontWeight, fontSize, etc.
            Text(
                "This is an example of a Jetpack Compose composable inside a classic Android " +
                        "view",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W900,
                    fontSize = 14.sp,
                    color = Color.Black
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
@Composable
@Preview
fun CardComponentWithMessagePreview() {
    CardComponentWithMessage()
}
