package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.core.Person
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getPersonList
import com.example.jetpackcompose.image.TitleComponent

class HorizontalScrollableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Column is a composable that places its children in a vertical sequence. We should
            // think of composable functions to be similar to lego blocks - each composable
            // function is in turn built up of smaller composable functions
            Column {
                // Title Component is a custom composable that we created which is capable of
                // rendering text on the screen in a certain font style & text size.
                TitleComponent("Horizontal Scrollable Carousel")
                HorizontalScrollableComponent(getPersonList())

                TitleComponent(
                    "Horizontal Scrolling Carousel where each item occupies the" +
                            " width of the screen"
                )
                HorizontalScrollableComponentWithScreenWidth(getPersonList())
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun HorizontalScrollableComponent(personList: List<Person>) {
    // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
    // This allows us to also control the scroll position and other scroll related properties.

    // remember calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. More details are available in the
    // comments below.
    val scrollState = rememberScrollState()
    // Row is a composable that places its children in a horizontal sequence. You
    // can think of it similar to a LinearLayout with the horizontal orientation.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we ask the HorizontalScroller
    // to occupy the entire available width.
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                state = scrollState,
            ),
        content = {
            // We iterate over each item from the personList and define what each item should
            // look like.
            for ((index, person) in personList.withIndex()) {
                // Card composable is a predefined composable that is meant to represent the card
                // surface as specified by the Material Design specification. We also configure it
                // to have rounded corners and apply a modifier.

                // You can think of Modifiers as implementations of the decorators pattern that are
                // used to modify the composable that its applied to. In this example, we assign a
                // padding of 16dp to the Card.
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = colors[index % colors.size],
                    modifier = Modifier.padding(16.dp)
                ) {
                    // The Text composable is pre-defined by the Compose UI library; you can use this
                    // composable to render text on the screen
                    Text(
                        person.name,
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        })
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun HorizontalScrollableComponentWithScreenWidth(personList: List<Person>) {
    // We create a ScrollState that's "remember"ed  to add proper support for a scrollable component.
    // This allows us to also control the scroll position and other scroll related properties.

    // remember calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. More details are available in the
    // comments below.
    val scrollState = rememberScrollState()
    // Row is a composable that places its children in a horizontal sequence. You
    // can think of it similar to a LinearLayout with the horizontal orientation.
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                state = scrollState,
            ),
        content = {
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
            val resources = context.resources
            val displayMetrics = resources.displayMetrics
            // Compute the screen width using the actual display width and the density of the display.
            val screenWidth = displayMetrics.widthPixels / displayMetrics.density
            val spacing = 16.dp
            // Row is a composable that places its children in a horizontal sequence. You
            // can think of it similar to a LinearLayout with the horizontal orientation.
            Row {
                // We iterate over each item from the personList and define what each item should
                // look like.
                for ((index, person) in personList.withIndex()) {
                    // Card composable is a predefined composable that is meant to represent the card
                    // surface as specified by the Material Design specification. We also configure it
                    // to have rounded corners and apply a modifier.

                    // You can think of Modifiers as implementations of the decorators pattern that are
                    // used to modify the composable that its applied to. In this example, we assign a
                    // padding of 16dp to the Card.
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = colors[index % colors.size],
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Column is a composable that places its children in a vertical sequence. You
                        // can think of it similar to a LinearLayout with the vertical orientation.
                        // In addition we also pass a few modifiers to it.

                        // To ensure that the item occupies the entire screen, we make sure that the
                        // width of the column is equal to the computed screenWidth. We subtract
                        // some spacing to make the other item slightly visible.
                        Column(
                            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // The Text composable is pre-defined by the Compose UI library; you can use
                            // this composable to render text on the screen
                            Text(
                                text = person.name,
                                modifier = Modifier.padding(16.dp),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }
            }
        })
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
@Preview("Horizontal Scrollable Carousel")
@Composable
fun HorizontalScrollableComponentPreview() {
    HorizontalScrollableComponent(
        getPersonList()
    )
}

@Preview("Horizontal Scrolling Carousel where each item occupies the width of the screen")
@Composable
fun HorizontalScrollableComponentWithScreenWidthPreview() {
    HorizontalScrollableComponentWithScreenWidth(
        getPersonList()
    )
}
