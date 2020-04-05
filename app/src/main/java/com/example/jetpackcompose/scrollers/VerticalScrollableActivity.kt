package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
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
            AdapterListingScrollableComponent(
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
fun AdapterListingScrollableComponent(personList: List<Person>) {
    // AdapterList is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecylerView tries to do as it's more optimized
    // than the VerticalScroller.
    AdapterList(data = personList, modifier = LayoutHeight.Fill) { person ->
        // TODO(vinaygaba) Replace this with an index callback once its available.
        val index = personList.indexOf(person)
        Row(modifier = LayoutPadding(16.dp) + LayoutWidth.Fill) {
            Card(shape = RoundedCornerShape(4.dp), color = colors[index % colors.size],
                modifier = LayoutWidth.Fill) {
                Text(
                    person.name, style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ), modifier = LayoutPadding(16.dp)
                )
            }
        }
    }
}

@Composable
fun VerticalScrollableComponent(personList: List<Person>) {
    // Vertical scroller is a composable that adds the ability to scroll through the
    // child composables that are declared inside it. One caveat here is that this is not optimized
    // to recycle the views. It is more similar to [ScrollView] and should not be thought of as a
    // replacement for [RecyclerView]. Instead look at the example below that uses AdapterList
    // which is supposed to be more efficient.
    VerticalScroller {
        // Column is a composable that places its children in a vertical sequence.
        Column {
            for ((index,person) in personList.withIndex()) {
                Row(modifier = LayoutWidth.Fill + LayoutPadding(16.dp)) {
                    Card(shape = RoundedCornerShape(4.dp), color = colors[index % colors.size],
                        modifier = LayoutWidth.Fill) {
                        Text(
                            person.name, style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            ), modifier = LayoutPadding(16.dp)
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
fun AdapterListingScrollableComponentPreview() {
    AdapterListingScrollableComponent(
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

