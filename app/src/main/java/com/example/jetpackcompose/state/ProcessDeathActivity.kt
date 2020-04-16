package com.example.jetpackcompose.state

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.input.KeyboardType
import androidx.ui.input.OffsetMap
import androidx.ui.input.TransformedText
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.aspectRatio
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.material.Card
import androidx.ui.material.Surface
import androidx.ui.savedinstancestate.rememberSavedInstanceState
import androidx.ui.savedinstancestate.savedInstanceState
import androidx.ui.text.AnnotatedString
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.image.TitleComponent

class ProcessDeathActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column {
                TitleComponent("Enter your credit card number below")
                ProcessDeathComponent()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ProcessDeathComponent() {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to occupuy the entire available width and height using
    // Modifier.fillMaxSize() and give center gravity to the content inside this box.
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        // Card composable is a predefined composable that is meant to represent the card surface as
        // specified by the Material Design specification. We also configure it to have rounded
        // corners and apply few modifiers to alter the dimensions of this card.
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.preferredWidth(300.dp) + Modifier.aspectRatio(16/9f),
            color = colors[0]) {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.

            // We use Arrangement.SpaceBetween to place the children of the column such that they
            // are spaced evenly across the main axis, without free space before the first child or
            // after the last child.
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                // TextField is a composable that is capable of accepting text user input. It renders the
                // value that you pass to the "value" field. In order to update this as the user is
                // typing a new string, we make use of the state delegate. However, the state delegate we
                // use here is slightly different than the one we used in [StateActivity]. Here, we make
                // use of the savedInstanceState composable. Read one to learn more about the difference.

                // Reacting to state changes is the core behavior of Compose. Any composable that reads the
                // value of the textValue field will recompose whenever this value is changed. In this
                // example, since the TextField is reading the value from the textValue value, and
                // that's also the value that we update as the user types (through the onValueChange
                // lambda), this composable is redrawn and updated with the latest value.

                // Since we are trying to retain the state even in a scenario when the process that our
                // app was running in is shut down or when the activity is recreated (like in the case of
                // orientation changes), we use the savedInstanceState composable. It's created for
                // supporting the exact use case.

                // Now you may ask that why isn't this the default behavior of the state composable itself
                // and why is there a separate savedInstanceState composable? Great question!
                // The main reason is that this has a limit on how much data you can store inside it and
                // is meant to only support data with a size less than 500kb
                // (https://stackoverflow .com/questions/8552514/is-there-any-limit-of-bundle-in-android)
                // Thus you should be careful about what data you store inside.
                var textValue by savedInstanceState { "1234567812345678" }
                TextField(value = TextFieldValue(textValue),
                    modifier = Modifier.padding(16.dp),
                    textStyle = TextStyle(color = Color.White, fontFamily = FontFamily.Serif,
                        fontSize = 25.sp, fontWeight = FontWeight.Bold),
                    keyboardType = KeyboardType.Number,
                    // Visual transformation is used to modify the visual output of the input field. In
                    // this example, I'm using a custom visual transformation - the
                    // CreditCardVisualTransformation. All it does is that it transforms the input
                    // to have a space added after every 4 characters. The text itself isn't
                    // altered, just its visual appearance is. You can easily created you own visual
                    // transformations by implementing the VisualTransformation interface like we did
                    // for CreditCardVisualTransformation.
                    visualTransformation = CreditCardVisualTransformation(),
                    // Update value of textValue with the latest value of the text field
                    onValueChange = { newTextValue ->
                        textValue = newTextValue.text
                    }
                )
                // Text is a predefined composable that does exactly what you'd expect it to -
                // display text on the screen. It allows you to customize its appearance using
                // the style property.
                Text(
                    text = "John Doe",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

// Visual transformation is used to modify the visual output of the input field. In this example,
// I'm using a custom visual transformation - the CreditCardVisualTransformation. All it does is
// that it transforms the input to have a space added after every 4 characters. The text itself isn't
// altered, just its visual appearance is. You can easily created you own visual transformations by
// implementing the VisualTransformation interface like we did for CreditCardVisualTransformation.
class CreditCardVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            // Regex used to add a space after every 4 characters.
            AnnotatedString(text.text.replace("....".toRegex(), "$0 ")),
            creditCardOffsetMap
        )
    }
}

// Offset map is used to change the position of the cursor based on the transormation being
// applied. If no offset is needed for the cursor position, return the same offset value. In the
// case of CreditCardVisualTransformation, since we add a space character after every 4
// characters, we need to move the cursor accordingly. For example, if we added 3 space
// characters, we need to account for that and move the cursor offset by 3 characters.
val creditCardOffsetMap =  object: OffsetMap {
    override fun originalToTransformed(offset: Int) = offset + (offset/4)
    override fun transformedToOriginal(offset: Int) = offset - (offset/4)
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
fun ProcessDeathComponentPreview() {
    ProcessDeathComponent()
}
