package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.padding
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
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column() {
                // Title Component is a custom composable that we created which is capable of
                // rendering text on the screen in a certain font style & text size.
                TitleComponent("This is a simple bottom navigation bar that always shows label")
                // Card composable is a predefined composable that is meant to represent
                // the card surface as specified by the Material Design specification. We
                // also configure it to have rounded corners and apply a modifier.

                // You can think of Modifiers as implementations of the decorators pattern that
                // are used to modify the composable that its applied to. In this example, we assign
                // a padding of 8dp to the Card.
                Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
                    BottomNavigationAlwaysShowLabelComponent()
                }
                TitleComponent("This is a bottom navigation bar that only shows label for " +
                        "selected item")
                Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
                    BottomNavigationOnlySelectedLabelComponent()
                }
            }
        }
    }
}

val listItems = listOf("Games", "Apps", "Movies", "Books")

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun BottomNavigationAlwaysShowLabelComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var selectedIndex by state { 0 }
    // BottomNavigation is a component placed at the bottom of the screen that represents primary
    // destinations in your application.
    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            // A composable typically used as part of BottomNavigation. Since BottomNavigation
            // is usually used to represent primary destinations in your application,
            // BottomNavigationItem represents a singular primary destination in your application.
            BottomNavigationItem(
                icon = {
                    // Simple composable that allows you to draw an icon on the screen. It
                    // accepts a vector asset as the icon.
                    Icon(asset = Icons.Filled.Favorite)
                },
                text = {
                    // Text is a predefined composable that does exactly what you'd expect it to -
                    // display text on the screen. It allows you to customize its appearance using the
                    // style property.
                    Text(text = label)
                },
                // Update the selected index when the BottomNavigationItem is clicked
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index }
            )
        }
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var selectedIndex by state { 0 }
    // BottomNavigation is a component placed at the bottom of the screen that represents primary
    // destinations in your application.
    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            // A composable typically used as part of BottomNavigation. Since BottomNavigation
            // is usually used to represent primary destinations in your application,
            // BottomNavigationItem represents a singular primary destination in your application.
            BottomNavigationItem(
                icon = {
                    // Simple composable that allows you to draw an icon on the screen. It
                    // accepts a vector asset as the icon.
                    Icon(asset = Icons.Filled.Favorite)
                },
                text = {
                    // Text is a predefined composable that does exactly what you'd expect it to -
                    // display text on the screen. It allows you to customize its appearance using the
                    // style property.
                    Text(text = label)
                },
                selected = selectedIndex == index,
                // Update the selected index when the BottomNavigationItem is clicked
                onSelected = { selectedIndex = index },
                // Setting this to false causes the label to be show only for the navigation item
                // that is currently selected, like in the BottomNavigationAlwaysShowLabelComponent
                // component.
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
