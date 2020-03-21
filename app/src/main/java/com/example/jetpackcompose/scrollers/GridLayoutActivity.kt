package com.example.jetpackcompose.scrollers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Table
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
            VerticalScroller {
                GridLayoutComponent(getPersonList())
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun GridLayoutComponent(personList: List<Person>) {
    val numberOfColumns = 2
    // Table is a composable that adds the ability to scroll through the child composables that are
    // declared inside it. One caveat here is that this is not optimized to recycle the views and
    // loads everything non-lazily
    Table(columns = numberOfColumns) {
        for (i in personList.indices step numberOfColumns) {
            tableRow {
                for (j in 0 until numberOfColumns) {
                    Card(shape = RoundedCornerShape(4.dp), color = colors[(i + j) % colors.size],
                        modifier = LayoutPadding(16.dp) + LayoutWidth.Fill + LayoutHeight(100.dp)
                    ) {
                        Text(text = personList[i + j].name,
                            modifier = LayoutPadding(16.dp) + LayoutAlign.CenterVertically,
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

// Android Studio lets you preview your composable functions within the IDE itself, instead of
// needing to download the app to an Android device or emulator. This is a fantastic feature as you
// can preview all your custom components(read composable functions) from the comforts of the IDE.
// The main restriction is, the composable function must not take any parameters. If your composable
// function requires a parameter, you can simply wrap your component inside another composable
// function that doesn't take any parameters and call your composable function with the appropriate
// params. Also, don't forget to annotate it with @Preview & @Composable annotations.
@Preview
@Composable
fun GridLayoutComponentPreview() {
    GridLayoutComponent(
        getPersonList()
    )
}