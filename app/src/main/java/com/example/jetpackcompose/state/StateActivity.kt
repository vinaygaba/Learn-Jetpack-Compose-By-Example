package com.example.jetpackcompose.state

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class StateActivity: AppCompatActivity() {
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
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the counter. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var counter by state { 0 }
    TitleComponent("Example using state class to store state")
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier(Modifier.fillMaxWidth()) to the Row and ask it to extend the
    // full width available to it. Alternatively, we could've assigned a fixed width to this row
    // using Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(
            modifier = Modifier.padding(16.dp) + Modifier.weight(1f),
            elevation = 5.dp,
            // We increment the counter every time this button is clicked.
            onClick = {
                counter++
            }) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Increment", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp) + Modifier.weight(1f),
            elevation = 5.dp,
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
 * In this example, we are making use of a data class called [CounterState] that's annotated with
 * the [Model] class. We use this instead of using the state delegate composable that we saw in the
 * example above. Both the state composable & the Model annotation do the same thing - they work
 * as a mechanism to allow efficient recomposition & hold state that allows other composables to
 * observe the state to recompose whenever the value is updated. You would want to use the @Model
 * annotation whenever your model class is trying to class multiple values and just use the state
 * composable like in the example above whenever you want to track a single value.
 */
@Composable
fun ModelComponent(counterState: CounterState = CounterState()) {
    TitleComponent("Example using Model class to store state")
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the LayoutWeight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(
            modifier = Modifier.padding(16.dp) + Modifier.weight(1f),
            elevation = 5.dp,
            onClick = {
                counterState.counter++
            }) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Increment", modifier = Modifier.padding(16.dp))
        }

        Button(
            modifier = Modifier.padding(16.dp) + Modifier.weight(1f),
            elevation = 5.dp,
            onClick = {
                counterState.counter = 0
            }) {
            Text(text = "Reset", modifier = Modifier.padding(16.dp))
        }
    }
    // This text composable is just used to display the current value of the counter.
    Text(text = "Counter value is ${counterState.counter}", modifier = Modifier.padding(16.dp))
}

@Model
class CounterState(var counter: Int = 0)

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
