package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.frames.modelListOf
import androidx.compose.getValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.FlowRow
import androidx.ui.layout.MainAxisAlignment
import androidx.ui.layout.SizeMode
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.core.Amenity
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getAmenityList
import com.example.jetpackcompose.image.TitleComponent

class FlowRowActivity: AppCompatActivity() {
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
                TitleComponent(title = "Tap to select options")
                SimpleFlowRow(getAmenityList())
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun SimpleFlowRow(amenityList: List<Amenity>) {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether the checkbox is checked. Any composable that reads the value of "selectedIndices"
    // will be recomposed any time the value changes. This ensures that only the composables that
    // depend on this will be redraw while the rest remain unchanged. This ensures efficiency and
    // is a performance optimization. It is inspired from existing frameworks like React.
    val selectedIndices by state { modelListOf<Int>() }
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the
    // Box to occupy the entire available width using the Modifier.fillMaxSize() modifier and 
    // also give it a padding of 4 dp.
    Box(modifier = Modifier.padding(4.dp) + Modifier.fillMaxSize() + Modifier) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Center,
            crossAxisSpacing = 16.dp,
            mainAxisSpacing = 16.dp,
            mainAxisSize = SizeMode.Expand
        ) {
            amenityList.forEachIndexed { index, amenity ->
                // Clickable wraps the child composable and enables it to react to a click through 
                // the onClick callback similar to the onClick listener that we are accustomed to on 
                // Android. Here, we just add the current index to the selectedIndices set every
                // time a user taps on it.
                Clickable(onClick = { selectedIndices.add(index) }) {
                    // Text is a predefined composable that does exactly what you'd expect it to - 
                    // display text on the screen. It allows you to customize its appearance using 
                    // style, fontWeight, fontSize, etc.
                    Text(
                        text = if (selectedIndices.contains(index)) "✓ ${amenity.name}" else amenity.name,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.drawBackground(
                            color = colors[index % colors.size], shape = RoundedCornerShape(15.dp)) +
                                Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
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
fun SimpleFlowRowPreview() {
    SimpleFlowRow(getAmenityList())
}
