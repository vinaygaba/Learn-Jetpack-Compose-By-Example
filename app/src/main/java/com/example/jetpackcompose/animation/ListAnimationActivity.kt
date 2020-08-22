package com.example.jetpackcompose.animation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getPersonList

class ListAnimationActivity: AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            ListAnimationComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@ExperimentalAnimationApi
@Composable
fun ListAnimationComponent() {
    val personList = getPersonList()
    var counter by remember { mutableStateOf(0) }
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation. 
    Column {
        // Row is a composable that places its children in a horizontal sequence. You
        // can think of it similar to a LinearLayout with the horizontal orientation.
        // In addition, we pass a modifier to the Row composable. You can think of
        // Modifiers as implementations of the decorators pattern that  are used to
        // modify the composable that its applied to. In this example, we configure the
        // Row to occupify the entire available width using Modifier.fillParentMaxWidth() and 
        // apply a padding of 16dp.
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(), 
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    counter++
                },
                enabled = counter <= personList.lastIndex
            ) {
                Text(text = "Add person")
            }
            Button(
                onClick = {
                    counter--
                },
                enabled = counter >= 1
            ) {
                Text(text = "Delete person")
            }
        }
        // LazyColumnItems is a vertically scrolling list that only composes and lays out the currently
        // visible items. This is very similar to what RecylerView tries to do as it's more optimized
        // than the VerticalScroller.
        LazyColumnForIndexed(items = personList, modifier = Modifier.fillMaxWidth()) { index, person ->
            Log.e("Status $index", "${(personList.size - counter) <= index}")
            AnimatedVisibility(
                visible = (personList.size - counter) <= index,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                // Row is a composable that places its children in a horizontal sequence. You
                // can think of it similar to a LinearLayout with the horizontal orientation.
                // In addition, we pass a modifier to the Row composable. You can think of
                // Modifiers as implementations of the decorators pattern that  are used to
                // modify the composable that its applied to. In this example, we configure the
                // Row to occupify the entire available width using Modifier.fillParentMaxWidth().
                Row(modifier = Modifier.fillParentMaxWidth()) {
                    // Card composable is a predefined composable that is meant to represent the 
                    // card surface as specified by the Material Design specification. We also 
                    // configure it to have rounded corners and apply a modifier.
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = colors[index % colors.size],
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
@Composable
fun ListAnimationComponentPreview() {
    
}
