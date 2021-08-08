package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AlertDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Column is a composable that places its children in a vertical sequence. We should
            // think of composable functions to be similar to lego blocks - each composable
            // function is in turn built up of smaller composable functions
            Column {
                // Here, ClickableText is a @Composable function which is going to describe the
                // contents of this activity that will be rendered on the screen.
                ClickableText()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ClickableText() {
    // Reacting to state changes is the core behavior of Compose. You will notice a couple new
    // keywords that are compose related - remember & mutableStateOf.remember{} is a helper
    // composable that calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. Next, you can think of
    // mutableStateOf as an observable value where updates to this variable will redraw all
    // the composable functions that access it. We don't need to explicitly subscribe at all. Any
    // composable that reads its value will be recomposed any time the value
    // changes. This ensures that only the composables that depend on this will be redraw while the
    // rest remain unchanged. This ensures efficiency and is a performance optimization. It
    // is inspired from existing frameworks like React.
    var showPopup by remember { mutableStateOf(false) }
    // Column with clickable modifier wraps the child composable and enables it to react to a click
    // through the onClick callback similar to the onClick listener that we are accustomed to
    // on Android.
    // Here, we just change the value of showPopup to be true every time we click on the text that
    // says "Click to see Popup"
    Column(Modifier.clickable(onClick = { showPopup = true }), content = {
        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to. In the example below, we add a padding of
        // 8dp to the Card composable and 16dp to the Text composable.
        Card(
            shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp),
            backgroundColor = Color.LightGray
        ) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(
                text = "Click to see dialog", modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif
                )
            )
        }
    })

    // A lambda that toggles the showPopup value to off. We pass it to the onDismiss equivalent
    // callback of the AlertDialog.
    val onPopupDismissed = { showPopup = false }

    // We want to show the popup only if the showPopup variable is toggled to true. Since Jetpack
    // Compose uses the declarative way of programming, we can easily decide what needs to shows 
    // vs hidden based on which branch of code is being executed. In this example, we display the
    // AlertDialog only when the showPopup variable is set to true or else this branch is not 
    // executed at all and thus the alert dialog remains hidden. 
    if (showPopup) {
        // Predefined composable provided by the material implementations of Jetpack Compose. It
        // shows a simple alert dialog on the screen if this code path is executed (i.e showPopup
        // variable is true)
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            text = {
                Text("Congratulations! You just clicked the text successfully")
            },
            confirmButton = {
                // Button is a pre-defined Material Design implementation of a contained button -
                // https://material.io/design/components/buttons.html#contained-button.
                Button(
                    onClick = onPopupDismissed
                ) {
                    // The Button composable allows you to provide child composables that inherit
                    // this button functionality.
                    // The Text composable is pre-defined by the Compose UI library; you can use this
                    // composable to render text on the screen
                    Text(text = "Ok")
                }
            })
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
fun ClickableTextPreview() {
    Column {
        ClickableText()
    }
}
