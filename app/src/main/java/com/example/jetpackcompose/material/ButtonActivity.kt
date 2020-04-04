package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.material.Button
import androidx.ui.material.OutlinedButton
import androidx.ui.material.TextButton
import androidx.ui.unit.dp

class ButtonActivity: AppCompatActivity() {
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
            // column to occupy the entire available height & width  sing the LayoutSize.Fill
            // modifier.
            Column(modifier = LayoutSize.Fill) {
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
    // modify the composable that its applied to. In this example, we assign a LayoutPadding of
    // 16dp to the Button.
    Button(
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp,
        onClick = {}) {
        // The Button composable allows you to provide child composables that inherit this button
        // functiionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Simple button", modifier = LayoutPadding(16.dp))
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
    // modify the composable that its applied to. In this example, we assign a LayoutPadding of
    // 16dp to the Button.
    Button(
        onClick = {},
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp,
        // Provide a border for this button
        border = Border(size = 5.dp, brush = SolidColor(Color.Black))) {
        // The Button composable allows you to provide child composables that inherit this button
        // functiionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Simple button with border", modifier = LayoutPadding(16.dp))
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
    // modify the composable that its applied to. In this example, we assign a LayoutPadding of
    // 16dp to the Button.
    Button(
        onClick = {},
        modifier = LayoutPadding(16.dp),
        // Provide a custom shape for this button. In this example. we specify the button to have
        // round corners of 16dp radius.
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp) {
        // The Button composable allows you to provide child composables that inherit this button
        // functiionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Button with rounded corners", modifier = LayoutPadding(16.dp))
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
    // modify the composable that its applied to. In this example, we assign a LayoutPadding of
    // 16dp to the Button.
    OutlinedButton(
        onClick = {},
        modifier = LayoutPadding(16.dp)) {
        // The Button composable allows you to provide child composables that inherit this button
        // functiionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Outlined Button", modifier = LayoutPadding(16.dp))
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
    // modify the composable that its applied to. In this example, we assign a LayoutPadding of
    // 16dp to the Button.
    TextButton(
        onClick = {},
        modifier = LayoutPadding(16.dp)) {
        // The Button composable allows you to provide child composables that inherit this button
        // functiionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Text Button", modifier = LayoutPadding(16.dp))
    }
}