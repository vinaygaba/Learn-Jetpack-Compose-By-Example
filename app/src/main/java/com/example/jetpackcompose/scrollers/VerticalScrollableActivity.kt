package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.core.Person
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getPersonList

class VerticalScrollableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            LazyColumnItemsScrollableComponent(
                getPersonList()
            )
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LazyColumnItemsScrollableComponent(personList: List<Person>) {
    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = personList, itemContent = { person ->
            // TODO(vinaygaba) Replace this with an index callback once its available.
            val index = personList.indexOf(person)
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            // In addition, we pass a modifier to the Row composable. You can think of
            // Modifiers as implementations of the decorators pattern that  are used to
            // modify the composable that its applied to. In this example, we configure the
            // Row to occupify the entire available width using Modifier.fillMaxWidth() and also give
            // it a padding of 16dp.
            Row(modifier = Modifier.fillParentMaxWidth()) {
                // Card composable is a predefined composable that is meant to represent the card surface as
                // specified by the Material Design specification. We also configure it to have rounded
                // corners and apply a modifier.
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = colors[index % colors.size],
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp)
                ) {
                    // Text is a predefined composable that does exactly what you'd expect it to -
                    // display text on the screen. It allows you to customize its appearance using
                    // the style property.
                    Text(
                        person.name, style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(16.dp)
                    )
                }
            }
        })
    }
}

@Composable
fun VerticalScrollableComponent(personList: List<Person>) {
    // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
    // This allows us to also control the scroll position and other scroll related properties.

    // remember calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. More details are available in the
    // comments below.
    val scrollState = rememberScrollState()
    // Column is a composable that places its children in a vertical sequence.
    Column(
        modifier = Modifier.scrollable(
            state = scrollState,
            orientation = Orientation.Vertical
        )
    ) {
        for ((index, person) in personList.withIndex()) {
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            // In addition, we pass a modifier to the Row composable. You can think of
            // Modifiers as implementations of the decorators pattern that  are used to
            // modify the composable that its applied to. In this example, we configure the
            // Row to occupify the entire available width using Modifier.fillMaxWidth() and also
            // give it a padding of 16dp.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Card composable is a predefined composable that is meant to represent the card surface as
                // specified by the Material Design specification. We also configure it to have rounded
                // corners and apply a modifier.
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = colors[index % colors.size],
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Text is a predefined composable that does exactly what you'd expect it to
                    // display text on the screen. It allows you to customize its appearance
                    // using the style property.
                    Text(
                        person.name, style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(16.dp)
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
fun LazyColumnItemsScrollableComponentPreview() {
    LazyColumnItemsScrollableComponent(
        getPersonList()
    )
}

@Preview
@Composable
fun VerticalScrollableComponentPreview() {
    VerticalScrollableComponent(
        getPersonList()
    )
}

