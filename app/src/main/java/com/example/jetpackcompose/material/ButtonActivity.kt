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
// functions can only be called from within the scope of other composable functions.
@Composable
fun SimpleButtonComponent() {
    Button(
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp,
        onClick = {}) {
        Text(text = "Simple button", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun SimpleButtonWithBorderComponent() {
    Button(
        onClick = {},
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp,
        border = Border(size = 5.dp, brush = SolidColor(Color.Black))) {
        Text(text = "Simple button with border", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun RoundedCornerButtonComponent() {
    Button(
        onClick = {},
        modifier = LayoutPadding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp) {
        Text(text = "Button with rounded corners", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun OutlinedButtonComponent() {
    OutlinedButton(
        onClick = {},
        modifier = LayoutPadding(16.dp)) {
        Text(text = "Outlined Button", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun TextButtonComponent() {
    TextButton(
        onClick = {},
        modifier = LayoutPadding(16.dp)) {
        Text(text = "Text Button", modifier = LayoutPadding(16.dp))
    }
}