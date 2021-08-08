package com.example.jetpackcompose.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.InternalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.core.hideKeyboard
import com.example.jetpackcompose.image.TitleComponent

class TextFieldActivity : AppCompatActivity() {
    @InternalTextApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
            // This allows us to also control the scroll position and other scroll related properties.

            // remember calculates the value passed to it only during the first composition. It then
            // returns the same value for every subsequent composition. More details are available in the
            // comments below.
            val scrollState = rememberScrollState()
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
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

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@InternalTextApi
@Composable
fun SimpleTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // BasicTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate.

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
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your text here")) }
        BasicTextField(
            value = textValue,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
@InternalTextApi
@Composable
fun CustomStyleTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // BasicTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate.

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
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your text here")) }
        BasicTextField(
            value = textValue,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // You can also customize the appearance of the TextInput by passing a TextStyle
            // configuration to the TextField composable. If you don't pass this, it's just going
            // to use the default values for all the properties.
            textStyle = TextStyle(
                color = Color.Blue,
                fontSize = 20.sp,
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
@InternalTextApi
@Composable
fun NumberTextInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // BasicTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate.

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
        var textValue by remember { mutableStateOf(TextFieldValue("123")) }
        BasicTextField(
            value = textValue,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // Setting the keyboard type allows you to configure what kind of data you can input
            // in this TextInput. Some examples are number, phone, email, password, etc.
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@InternalTextApi
@Composable
fun SearchImeActionInputComponent() {
    // LocalContext is a LocalComposition for accessting the context value that we are used to using
    // in Android.

    // LocalComposition is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use LocalComposition. In this example, we use the
    // LocalContext to get hold of the Context object. In order to get access to the latest
    // value of the LocalComposition, use the "current" property eg - LocalContext.current. Some other
    // examples of common LocalComposition's are LocalTextInputService,LocalDensity, etc.
    val context = LocalContext.current

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
        // BasicTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate.

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
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your search query here")) }
        BasicTextField(
            value = textValue,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // Changing the imeAction allows you to change the primary icon of the keyboard which
            // is typically shown in the bottom right corner of the keyboard. Some examples of
            // ImeActions are search, send, done, go, etc.
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                textValue = it
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    hideKeyboard(context)
                }
            )
        )
    }
}


// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@InternalTextApi
@Composable
fun PasswordVisualTransformationInputComponent() {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        // BasicTextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate.

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
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your password here")) }
        BasicTextField(
            value = textValue,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
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
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
@InternalTextApi
@Preview
@Composable
fun SimpleTextInputComponentPreview() {
    SimpleTextInputComponent()
}

@InternalTextApi
@Preview
@Composable
fun CustomStyleTextInputComponentPreview() {
    CustomStyleTextInputComponent()
}

@InternalTextApi
@Preview
@Composable
fun NumberTextInputComponentPreview() {
    NumberTextInputComponent()
}

@InternalTextApi
@Preview
@Composable
fun SearchImeActionInputComponentPreview() {
    SearchImeActionInputComponent()
}

@InternalTextApi
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



