package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.Modifier.Companion
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.Table
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.wrapContentHeight
import androidx.ui.material.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.Person
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getPersonList

class GridLayoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Vertical scroller is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            VerticalScroller {
                GridLayoutComponent(getPersonList())
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun GridLayoutComponent(personList: List<Person>) {
    val numberOfColumns = 2
    // Table is a composable that adds the ability to scroll through the child composables that are
    // declared inside it. One caveat here is that this is not optimized to recycle the views and
    // loads everything non-lazily
    Table(columns = numberOfColumns) {
        for (i in personList.indices step numberOfColumns) {
            // This allows to to create a new row in the Table and specify the composables inside
            // this row
            tableRow {
                for (j in 0 until numberOfColumns) {
                    // Card composable is a predefined composable that is meant to represent the
                    // card surface as specified by the Material Design specification. We also
                    // configure it to have rounded corners and apply a modifier.

                    // You can think of Modifiers as implementations of the decorators pattern that
                    // are used to modify the composable that its applied to. In the example below,
                    // we add a padding of 16dp, specify it to occupy the entire available width
                    // using Modifier.fillMaxWidth() & give it a height of 100dp.
                    Card(shape = RoundedCornerShape(4.dp), color = colors[(i + j) % colors.size],
                        modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth() +
                                Modifier.preferredHeight(100.dp)
                    ) {
                        // The Text composable is pre-defined by the Compose UI library; you can use
                        // this composable to render text on the screen
                        Text(text = personList[i + j].name,
                            modifier = Modifier.padding(16.dp) + Modifier.wrapContentHeight(
                                Alignment.Center
                            ),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
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
fun GridLayoutComponentPreview() {
    GridLayoutComponent(
        getPersonList()
    )
}
