package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.BottomAppBar
import androidx.ui.material.Card
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.core.colors

class FixedActionButtonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            ScaffoldWithBottomBarAndCutout()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ScaffoldWithBottomBarAndCutout() {
    // remember is a composable that allows you to store values that survive recomposition. That
    // means that the scaffoldState variable will continue to hold its value even if the
    // ScaffoldWithBottomBarAndCutout is recomposed. The value is calculated only during
    // the first composition and every subsequent recomposition returns the value produced by the
    // original composition.

    // ScaffoldState is a @Model class that holds basic screen state relevant to the Scaffold
    // composable eg. drawerState i.e whether the drawer is open or closed.
    val scaffoldState = remember { ScaffoldState() }

    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val fabShape = RoundedCornerShape(50)

    // Scaffold is a pre-defined composable that implements the basic material design visual
    // layout structure. It takes in child composables for all the common elements that you see
    // in an app using material design - app bar, bottom app bar, floating action button, etc. It
    // also takes care of laying out these child composables in the correct positions - eg bottom
    // app bar is automatically placed at the bottom of the screen even though I didn't specify
    // that explicitly.
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = { TopAppBar(title = { Text("Scaffold Examples") }) },
        bottomAppBar = { fabConfiguration ->
            // We specify the shape of the FAB bu passing a shape composable (fabShape) as a
            // parameter to cutoutShape property of the BottomAppBar. It automatically creates a
            // cutout in the BottomAppBar based on the shape of the Floating Action Button.
            BottomAppBar(fabConfiguration = fabConfiguration, cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                // We specify the same shape that we passed as the cutoutShape above.
                shape = fabShape,
                // We use the secondary color from the current theme. It uses the defaults when
                // you don't specify a theme (this example doesn't specify a theme either hence
                // it will just use defaults. Look at DarkModeActivity if you want to see an
                // example of using themes.
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(asset = Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButtonPosition = Scaffold.FabPosition.CenterDocked,
        bodyContent = { modifier ->
            // Vertical scroller is a composable that adds the ability to scroll through the
            // child views
            VerticalScroller {
                // Column is a composable that places its children in a vertical sequence. You
                // can think of it similar to a LinearLayout with the vertical orientation.
                Column(modifier) {
                    repeat(100) {
                        // Card composable is a predefined composable that is meant to represent
                        // the card surface as specified by the Material Design specification. We
                        // also configure it to have rounded corners and apply a modifier.
                        Card(color = colors[it % colors.size],
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Spacer(modifier = Modifier.fillMaxWidth() + Modifier.preferredHeight(200.dp))
                        }
                    }
                }
            }
        }
    )
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
@Preview("Fixed Aaction Button Example")
@Composable
fun ScaffoldWithBottomBarAndCutoutPreview() {
    ScaffoldWithBottomBarAndCutout()
}
