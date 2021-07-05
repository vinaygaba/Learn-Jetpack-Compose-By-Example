package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In the example below, we configure the
            // column to occupy the entire available height & width using the Modifier.fillMaxSize()
            // modifier.
            Column(modifier = Modifier.fillMaxSize()) {
                SimpleButtonComponent()
                SimpleButtonWithBorderComponent()
                RoundedCornerButtonComponent()
                OutlinedButtonComponent()
                TextButtonComponent()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SimpleButtonComponent() {
    // Button is a pre-defined Material Design implementation of a contained button -
    // https://material.io/design/components/buttons.html#contained-button.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Button.
    Button(
        modifier = Modifier.padding(16.dp),
        elevation = ButtonDefaults.elevation(5.dp),
        onClick = {}) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Simple button", modifier = Modifier.padding(16.dp))
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SimpleButtonWithBorderComponent() {
    // Button is a pre-defined Material Design implementation of a contained button -
    // https://material.io/design/components/buttons.html#contained-button.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Button.
    Button(
        onClick = {},
        modifier = Modifier.padding(16.dp),
        elevation = ButtonDefaults.elevation(5.dp),
        // Provide a border for this button
        border = BorderStroke(width = 5.dp, brush = SolidColor(Color.Black))
    ) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Simple button with border", modifier = Modifier.padding(16.dp))
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun RoundedCornerButtonComponent() {
    // Button is a pre-defined Material Design implementation of a contained button -
    // https://material.io/design/components/buttons.html#contained-button.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Button.
    Button(
        onClick = {},
        modifier = Modifier.padding(16.dp),
        // Provide a custom shape for this button. In this example. we specify the button to have
        // round corners of 16dp radius.
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevation(5.dp),
    ) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Button with rounded corners", modifier = Modifier.padding(16.dp))
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun OutlinedButtonComponent() {
    // Button is a pre-defined Material Design implementation of a outlined button -
    // https://material.io/design/components/buttons.html#outlined-button.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Button.
    OutlinedButton(
        onClick = {},
        modifier = Modifier.padding(16.dp)
    ) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Outlined Button", modifier = Modifier.padding(16.dp))
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun TextButtonComponent() {
    // Button is a pre-defined Material Design implementation of a text button -
    // https://material.io/design/components/buttons.html#text-button.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Button.
    TextButton(
        onClick = {},
        modifier = Modifier.padding(16.dp)
    ) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Text Button", modifier = Modifier.padding(16.dp))
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
@Preview("Example showing a simple button")
@Composable
fun SimpleButtonComponentPreview() {
    Column {
        SimpleButtonComponent()
    }
}

@Preview("Example showing a button with border")
@Composable
fun SimpleButtonWithBorderComponentPreview() {
    Column {
        SimpleButtonWithBorderComponent()
    }
}

@Preview("Example showing a button with corners")
@Composable
fun RoundedCornerButtonComponentPreview() {
    Column {
        RoundedCornerButtonComponent()
    }
}

@Preview("Example showing a outline button")
@Composable
fun OutlinedButtonComponentPreview() {
    Column {
        OutlinedButtonComponent()
    }
}

@Preview("Example showing a text button")
@Composable
fun TextButtonComponentPreview() {
    Column {
        TextButtonComponent()
    }
}
