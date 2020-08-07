package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
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
    // LazyColumnItems is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecylerView tries to do as it's more optimized
    // than the VerticalScroller.
    LazyColumnFor(items = personList, modifier = Modifier.fillMaxHeight()) { person ->
        // TODO(vinaygaba) Replace this with an index callback once its available.
        val index = personList.indexOf(person)
        // Row is a composable that places its children in a horizontal sequence. You
        // can think of it similar to a LinearLayout with the horizontal orientation.
        // In addition, we pass a modifier to the Row composable. You can think of
        // Modifiers as implementations of the decorators pattern that  are used to
        // modify the composable that its applied to. In this example, we configure the
        // Row to occupify the entire available width using Modifier.fillMaxWidth() and also give
        // it a padding of 16dp.
        Row(modifier = Modifier.padding(16.dp).fillParentMaxWidth()) {
            // Card composable is a predefined composable that is meant to represent the card surface as
            // specified by the Material Design specification. We also configure it to have rounded
            // corners and apply a modifier.
            Card(
                shape = RoundedCornerShape(4.dp), color = colors[index % colors.size],
                modifier = Modifier.fillParentMaxWidth()
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
    }
}

@Composable
fun VerticalScrollableComponent(personList: List<Person>) {
    // ScrollableColumn is a composable that adds the ability to scroll through the
    // child composables that are declared inside it. One caveat here is that this is not optimized
    // to recycle the views. It is more similar to [ScrollView] and should not be thought of as a
    // replacement for [RecyclerView]. Instead look at the example below that uses LazyColumnItems
    // which is supposed to be more efficient.
    ScrollableColumn {
        // Column is a composable that places its children in a vertical sequence.
        Column {
            for ((index, person) in personList.withIndex()) {
                // Row is a composable that places its children in a horizontal sequence. You
                // can think of it similar to a LinearLayout with the horizontal orientation.
                // In addition, we pass a modifier to the Row composable. You can think of
                // Modifiers as implementations of the decorators pattern that  are used to
                // modify the composable that its applied to. In this example, we configure the
                // Row to occupify the entire available width using Modifier.fillMaxWidth() and also
                // give it a padding of 16dp.
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    // Card composable is a predefined composable that is meant to represent the card surface as
                    // specified by the Material Design specification. We also configure it to have rounded
                    // corners and apply a modifier.
                    Card(
                        shape = RoundedCornerShape(4.dp), color = colors[index % colors.size],
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

