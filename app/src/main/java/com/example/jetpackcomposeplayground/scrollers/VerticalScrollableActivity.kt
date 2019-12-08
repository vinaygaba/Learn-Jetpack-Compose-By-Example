package com.example.jetpackcomposeplayground.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.core.sp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeplayground.core.Person
import com.example.jetpackcomposeplayground.core.getPersonList

class VerticalScrollableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            VerticalScrollableComponent(
                getPersonList()
            )
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun VerticalScrollableComponent(personList: List<Person>) {
    // Vertical scroller is a composable that adds the ability to scroll through the
    // child composables that are declared inside it. One caveat here is that this is not optimized
    // to recycle the views. It is more similar to [ScrollView] and should not be thought of as a
    // replacement for [RecyclerView]
    VerticalScroller {
        Column {
            for(person in personList) {
                FlexRow(modifier = Spacing(16.dp)) {
                   expanded(1f) {
                       Card(shape = RoundedCornerShape(4.dp), color = Color.Black) {
                           Text(person.name, style = TextStyle(
                               color = Color.White,
                               fontSize = 20.sp
                           ), modifier = Spacing(16.dp))
                       }
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
fun VerticalScrollableComponentPreview() {
    VerticalScrollableComponent(
        getPersonList()
    )
}