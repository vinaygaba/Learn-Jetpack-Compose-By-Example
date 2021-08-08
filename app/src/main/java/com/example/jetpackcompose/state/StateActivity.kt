package com.example.jetpackcompose.state

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class StateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column(modifier = Modifier.fillMaxWidth()) {
                StateComponent()
                // A pre-defined composable that renders a thin line on the screen that makes it
                // easy to group contents
                Divider()
                ModelComponent()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun StateComponent() {
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
    var counter by remember { mutableStateOf(0) }
    TitleComponent("Example using state class to store state")
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier(Modifier.fillMaxWidth()) to the Row and ask it to extend the
    // full width available to it. Alternatively, we could've assigned a fixed width to this row
    // using Modifier.width(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            // We increment the counter every time this button is clicked.
            onClick = {
                counter++
            }) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Increment", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = {
                counter = 0
            }) {
            Text(text = "Reset", modifier = Modifier.padding(16.dp))
        }
    }

    // This text composable is just used to display the current value of the counter.
    Text(text = "Counter value is $counter", modifier = Modifier.padding(16.dp))
}

/**
 * In this example, we are making use of a data class called [CounterState] to hold the state
 * that we want our composables to observe. This example is very similar to the previous example
 * with the only difference that it uses a data class to hold the value of our state. In
 * addition, we use the copy() method of a data class to mutate the values of the data class.
 */
@Composable
fun ModelComponent() {
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
    var counterState by remember { mutableStateOf(CounterState()) }
    TitleComponent("Example using Model class to store state")
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.width(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = {
                counterState = counterState.copy(counter = counterState.counter + 1)
            }) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Increment", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp).weight(1f),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = {
                counterState = counterState.copy(counter = 0)
            }) {
            Text(text = "Reset", modifier = Modifier.padding(16.dp))
        }
    }
    // This text composable is just used to display the current value of the counter.
    Text(text = "Counter value is ${counterState.counter}", modifier = Modifier.padding(16.dp))
}

data class CounterState(val counter: Int = 0)

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Preview("Example using state delegate")
@Composable
fun StateComponentPreview() {
    Column(modifier = Modifier.fillMaxHeight()) {
        StateComponent()
    }
}

@Preview("Example using Model annotation")
@Composable
fun ModelComponentPreview() {
    Column(modifier = Modifier.fillMaxHeight()) {
        ModelComponent()
    }
}
