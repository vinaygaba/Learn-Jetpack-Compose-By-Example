package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.Person
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
            Column {
                TitleComponent("Horizontal Scrollable Carousel")
                HorizontalScrollableComponent(
                    getPersonList()
                )

                TitleComponent("Horizontal Scrolling Carousel where each item occupies the" +
                        " width of the screen")
                HorizontalScrollableComponentWithScreenWidth(
                    getPersonList()
                )
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun HorizontalScrollableComponent(personList: List<Person>) {
    HorizontalScroller {
        Row() {
            for(person in personList) {
                Card(shape = RoundedCornerShape(4.dp), color = Color.Black,
                    modifier = LayoutPadding(16.dp)
                ) {
                    Text(person.name,
                        modifier = LayoutPadding(16.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        ))
                }
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun HorizontalScrollableComponentWithScreenWidth(personList: List<Person>) {
    HorizontalScroller {
        val context = ContextAmbient.current
        val resources = context.resources
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels / displayMetrics.density
        val spacing = 16.dp
        Row {
            for(person in personList) {
                Card(shape = RoundedCornerShape(4.dp), color = Color.Black,
                    modifier = LayoutPadding(16.dp)) {
                    Container(width = screenWidth.dp - (spacing * 2)) {
                        Text(person.name,
                            modifier = LayoutPadding(16.dp),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp
                            ))
                    }
                }
            }
        }
    }
}

// Android Studio lets you preview your composable functions within the IDE itself, instead of
// needing to download the app to an Android device or emulator. This is a fantastic feature as you
// can preview all your custom components(read composable functions) from the comforts of the IDE.
// The main restriction is, the composable function must not take any parameters. If your composable
// function requires a parameter, you can simply wrap your component inside another composable
// function that doesn't take any parameters and call your composable function with the appropriate
// params. Also, don't forget to annotate it with @Preview & @Composable annotations.
@Preview
@Composable
fun HorizontalScrollableComponentPreview() {
    HorizontalScrollableComponent(
        getPersonList()
    )
}