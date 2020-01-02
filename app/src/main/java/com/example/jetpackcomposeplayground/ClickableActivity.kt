package com.example.jetpackcomposeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button

class ClickableActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Here, ClickableText is a @Composable function which is going to describe the contents
            // of this activity that will be rendered on the screen.
            ClickableText()
        }
    }
}

@Composable
fun ClickableText() {
    var showPopup by +state { false }
    // Clickable wraps the child composable and enables it to react to a click through the onClick
    // callback similar to the onClick listener that we are accustomed to on Android.
    // Here, we just change the value of showPopup to be true every time we click on the text that
    // says "Click to see Popup"
    Clickable(onClick = { showPopup = true }) {
        Text("Click to see Popup")
    }
    val onPopupDismissed = { showPopup = false }

    if (showPopup) {
        AlertDialog(
            onCloseRequest = onPopupDismissed,
            text = {
                Text("Congratulations! You just clicked the text successfully")
            },
            confirmButton = {
                Button(
                    text = "OK",
                    onClick = onPopupDismissed
                )
            })
    }
}