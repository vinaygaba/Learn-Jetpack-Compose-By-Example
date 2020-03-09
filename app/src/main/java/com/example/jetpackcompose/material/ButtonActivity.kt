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
import androidx.ui.material.Button
import androidx.ui.material.OutlinedButton
import androidx.ui.material.TextButton
import androidx.ui.unit.dp

class ButtonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                SimpleButtonComponent()
                SimpleButtonWithBorderComponent()
                RoundedCornerButtonComponent()
                OutlinedButtonComponent()
                TextButtonComponent()
            }
        }
    }
}

@Composable
fun SimpleButtonComponent() {
    Button(
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp) {
        Text(text = "Simple button", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun SimpleButtonWithBorderComponent() {
    Button(
        modifier = LayoutPadding(16.dp),
        elevation = 5.dp,
        border = Border(size = 5.dp, brush = SolidColor(Color.Black))) {
        Text(text = "Simple button with border", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun RoundedCornerButtonComponent() {
    Button(
        modifier = LayoutPadding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp) {
        Text(text = "Button with rounded corners", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun OutlinedButtonComponent() {
    OutlinedButton(
        modifier = LayoutPadding(16.dp)) {
        Text(text = "Outlined Button", modifier = LayoutPadding(16.dp))
    }
}

@Composable
fun TextButtonComponent() {
    TextButton(
        modifier = LayoutPadding(16.dp)) {
        Text(text = "Text Button", modifier = LayoutPadding(16.dp))
    }
}