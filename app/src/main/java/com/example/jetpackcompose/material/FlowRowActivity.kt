package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.core.Amenity
import com.example.jetpackcompose.core.getAmenityList
import com.example.jetpackcompose.image.TitleComponent

class FlowRowActivity : AppCompatActivity() {
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

// FlowRow is available in androidx.compose.foundation.layout as of modern Compose.

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
//    val selectedIndices = mutableStateListOf<Int>()
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the
    // Column to occupy the entire available width using the Modifier.fillMaxSize() modifier and 
    // also give it a padding of 4 dp.
    Column(modifier = Modifier.padding(4.dp).fillMaxSize()) {
        FlowRow(
            maxItemsInEachRow = Int.MAX_VALUE,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            amenityList.forEachIndexed { index, amenity ->
                Text(
                    text = amenity.name,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .background(
                            color = com.example.jetpackcompose.core.colors[index % com.example.jetpackcompose.core.colors.size],
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.width(0.dp))
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
