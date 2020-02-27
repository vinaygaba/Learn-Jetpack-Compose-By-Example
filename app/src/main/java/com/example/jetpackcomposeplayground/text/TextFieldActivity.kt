package com.example.jetpackcomposeplayground.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.TextField
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.PasswordVisualTransformation
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextDecoration
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.example.jetpackcomposeplayground.core.hideKeyboard
import com.example.jetpackcomposeplayground.image.TitleComponent

class TextFieldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerticalScroller {
                Column {
                    TitleComponent("This is a Simple Text Input field")
                    SimpleTextInputComponent()

                    TitleComponent("This is a TextInput with custom text style")
                    CustomStyleTextInputComponent()

                    TitleComponent("This is a TextInput suitable for typing numbers")
                    NumberTextInputComponent()

                    TitleComponent("This is a search view created using TextInput")
                    SearchImeActionInputComponent()

                    TitleComponent("This is a TextInput that uses a Password Visual Transformation")
                    PasswordVisualTransformationInputComponent()
                }
            }
        }
    }
}

@Composable
fun SimpleTextInputComponent() {
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp)) {
        var textValue by state { "Enter your text here" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Composable
fun CustomStyleTextInputComponent() {
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp)) {
        var textValue by state { "Enter your text here" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            textStyle = TextStyle(
                color = Color.Blue,
                fontSize = TextUnit.Companion.Sp(20),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            ),
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Composable
fun NumberTextInputComponent() {
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp)) {
        var textValue by state { "123" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            keyboardType = KeyboardType.Number,
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Composable
fun SearchImeActionInputComponent() {
    val context = ContextAmbient.current
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp),
        shape = RoundedCornerShape(5.dp)) {
        var textValue by state { "Enter your search query here" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            imeAction = ImeAction.Search,
            onImeActionPerformed = {
                hideKeyboard(context)
            },
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Composable
fun PasswordVisualTransformationInputComponent() {
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp)) {
        var textValue by state { "Enter your password here" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                textValue = it
            }
        )
    }
}