package com.example.jetpackcompose.text

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.autofill.AutofillNode
import androidx.ui.autofill.AutofillType
import androidx.ui.core.AutofillAmbient
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
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextDecoration
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.example.jetpackcompose.core.hideKeyboard
import com.example.jetpackcompose.image.TitleComponent

class TextFieldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
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

// Not working yet for some reason
@Composable
fun AutoFillTextInputComponent() {
    val autofillAmbient = AutofillAmbient.current
    Surface(color = Color.LightGray, modifier = LayoutPadding(16.dp)) {
        var textValue by state { "" }
        TextField(value = textValue,
            modifier = LayoutPadding(16.dp),
            keyboardType = KeyboardType.Email,
            onFocus = {
                autofillAmbient?.requestAutofillForNode(
                    autofillNode = AutofillNode(
                        autofillTypes = listOf(AutofillType.EmailAddress),
                        boundingBox = Rect(0, 0, 400, 400),
                        onFill = {
                            textValue = it
                        }
                    )
                )
            },
            onBlur = {
                autofillAmbient?.cancelAutofillForNode(autofillNode = AutofillNode(
                    autofillTypes = listOf(AutofillType.EmailAddress),
                    onFill = {}
                ))
            },
            imeAction = ImeAction.Done,
            onValueChange = {
                textValue = it
            }
        )
    }
}