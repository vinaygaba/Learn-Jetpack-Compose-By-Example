package com.example.jetpackcompose.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.ui.tooling.preview.Preview

class SimpleTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Box is a predefined convenience composable that allows you to apply common draw & layout
            // logic. In addition we also pass a few modifiers to it.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In the example below, we configure the
            // Box to occupy the entire available height & width using Modifier.fillMaxSize().
            Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center, children = {
                // Here, SimpleText is a Composable function which is going to describe the contents of
                // this activity that will be rendered on the screen.
                SimpleText("This is the Learn Jetpack Compose By Example tutorial")
            })
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun SimpleText(displayText: String) {
    // We should think of composable functions to be similar to lego blocks - each composable
    // function is in turn built up of smaller composable functions. Here, the Text() function is
    // pre-defined by the Compose UI library; you call that function to declare a text element
    // in your app.
    Text(text = displayText)
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
fun SimpleTextPreview() {
    SimpleText("This is the Learn Jetpack Compose By Example tutorial")
}
