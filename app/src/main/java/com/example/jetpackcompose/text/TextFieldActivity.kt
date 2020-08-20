package com.example.jetpackcompose.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.CoreTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
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
            // ScrollableColumn is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            ScrollableColumn {
                // Column is a composable that places its children in a vertical sequence. You
                // can think of it similar to a LinearLayout with the vertical orientation.
                Column {
                    // Title Component is a custom composable that we created which is capable of
                    // rendering text on the screen in a certain font style & text size.
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

                    TitleComponent("This is a filled TextInput field based on Material Design")
                    MaterialTextInputComponent()
                }
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SimpleTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // CoreTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your text here")) }
        CoreTextField(value = textValue,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            }
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun CustomStyleTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // CoreTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your text here")) }
        CoreTextField(value = textValue,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            // You can also customize the appearance of the TextInput by passing a TextStyle
            // configuration to the TextField composable. If you don't pass this, it's just going
            // to use the default values for all the properties.
            textStyle = TextStyle(
                color = Color.Blue,
                fontSize = TextUnit.Companion.Sp(20),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            ),
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            }
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun NumberTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // CoreTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var textValue by remember { mutableStateOf(TextFieldValue("123")) }
        CoreTextField(value = textValue,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            // Setting the keyboard type allows you to configure what kind of data you can input
            // in this TextInput. Some examples are number, phone, email, password, etc.
            keyboardType = KeyboardType.Number,
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            }
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SearchImeActionInputComponent() {
    // Ambient is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use Ambients. In this example, we use the
    // ContextAmbient to get hold of the Context object. In order to get access to the latest
    // value of the Ambient, use the "current" property eg - ContextAmbient.current. Some other
    // examples of common Ambient's are TextInputServiceAmbient, DensityAmbient,
    // CoroutineContextAmbient, etc.
    val context = ContextAmbient.current

    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(
        color = Color.LightGray, modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        // CoreTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your search query here")) }
        CoreTextField(value = textValue,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            // Changing the imeAction allows you to change the primary icon of the keyboard which
            // is typically shown in the bottom right corner of the keyboard. Some examples of
            // ImeActions are search, send, done, go, etc.
            imeAction = ImeAction.Search,
            onImeActionPerformed = {
                hideKeyboard(context)
            },
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            }
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun PasswordVisualTransformationInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // CoreTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your password here")) }
        CoreTextField(value = textValue,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            // Visual transformation is used to modify the visual output of the input field. In
            // this example, I'm using an existing visual transformation - the
            // PasswordVisualTransformation. All it does is that it transforms any input character
            // into "•". The text itself isn't altered, just its visual appearance is. You can
            // easily created you own visual transformations by implementing the
            // VisualTransformation interface.
            visualTransformation = PasswordVisualTransformation(),
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            }
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun MaterialTextInputComponent() {
    var textValue by remember { mutableStateOf(TextFieldValue("")) }

    // TextField is a composable that is capable of accepting text user input. It renders the
    // value that you pass to the "value" field. In order to update this as the user is
    // typing a new string, we make use of the state delegate. Reacting to state changes is
    // the core behavior of Compose. Any composable that reads the value of the textValue
    // field will recompose whenever this value is changed. In this example, since the
    // TextField is reading the value from the textValue value, and that's also the value
    // that we update as the user types (through the onValueChange lambda), this composable
    // is redrawn and updated with the latest value.

    // In particular, the FilledTextField is a Material Design implementation of the
    // Material Filled TextField - https://material.io/components/text-fields/#filled-text-field
    // It also gives us the ability to provide some placeholder text.

    // We also pass it a modifier. You can think of Modifiers as implementations of the decorators 
    // pattern that are used to modify the composable that its applied to. In this example, we 
    // assign a padding of 16dp and ask the FilledTextField composable to occupy the entire 
    // available width.
    TextField(
        value = textValue,
        onValueChange = { textValue = it },
        label = { Text("Enter Your Name") },
        placeholder = { Text(text = "John Doe") },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
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
fun SimpleTextInputComponentPreview() {
    SimpleTextInputComponent()
}

@Preview
@Composable
fun CustomStyleTextInputComponentPreview() {
    CustomStyleTextInputComponent()
}

@Preview
@Composable
fun NumberTextInputComponentPreview() {
    NumberTextInputComponent()
}

@Preview
@Composable
fun SearchImeActionInputComponentPreview() {
    SearchImeActionInputComponent()
}

@Preview
@Composable
fun PasswordVisualTransformationInputComponentPreview() {
    PasswordVisualTransformationInputComponent()
}

@Preview
@Composable
fun FilledTextInputComponentPreview() {
    MaterialTextInputComponent()
}



