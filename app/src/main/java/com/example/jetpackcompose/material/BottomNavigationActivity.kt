package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.BottomNavigation
import androidx.ui.material.BottomNavigationItem
import androidx.ui.material.Card
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class BottomNavigationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            Column() {
                TitleComponent("This is a simple bottom navigation bar that always shows label")
                Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
                    BottomNavigationAlwaysShowLabelComponent()
                }

                TitleComponent("This is a bottom navigation bar that only shows label for " +
                        "selected item")
                Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
                    BottomNavigationOnlySelectedLabelComponent()
                }
            }
        }
    }
}

val listItems = listOf("Games", "Apps", "Movies", "Books")

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun BottomNavigationAlwaysShowLabelComponent() {
    var selectedIndex by state { 0 }
    BottomNavigation(modifier = LayoutPadding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(icon = Icons.Filled.Favorite)
                },
                text = {
                    Text(text = label)
                },
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index }
            )
        }
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    var selectedIndex by state { 0 }
    BottomNavigation(modifier = LayoutPadding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(icon = Icons.Filled.Favorite)
                },
                text = {
                    Text(text = label)
                },
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index },
                // Setting this to false causes the label to be show only for the navigation item
                // that is currently selected.
                alwaysShowLabels = false)
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
fun BottomNavigationAlwaysShowLabelComponentPreview() {
    BottomNavigationAlwaysShowLabelComponent()
}

@Preview
@Composable
fun BottomNavigationOnlySelectedLabelComponentPreview() {
    BottomNavigationOnlySelectedLabelComponent()
}