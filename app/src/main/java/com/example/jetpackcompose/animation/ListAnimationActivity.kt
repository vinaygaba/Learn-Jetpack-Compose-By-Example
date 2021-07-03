package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.core.Person
import com.example.jetpackcompose.core.colors
import com.example.jetpackcompose.core.getPersonList

class ListAnimationActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListAnimationComponent(getPersonList())
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@ExperimentalAnimationApi
@Composable
fun ListAnimationComponent(personList: List<Person>) {
    // Reacting to state changes is the core behavior of Compose. You will notice a couple new 
    // keywords that are compose related - remember & mutableStateListOf.remember{} is a helper 
    // composable that calculates the value passed to it only during the first composition. It then 
    // returns the same value for every subsequent composition. Next, you can think of 
    // mutableStateListOf as an observable list where updates to this variable will redraw all 
    // the composable functions that access it. We don't need to explicitly subscribe at all. Any 
    // composable that reads the value of deletedPersonList will be recomposed any time the value 
    // changes. This ensures that only the composables that depend on this will be redraw while the 
    // rest remain unchanged. This ensures efficiency and is a performance optimization. It 
    // is inspired from existing frameworks like React.

    // We use this variable to hold the list of all Person objects that are deleted from the list
    // below.
    val deletedPersonList = remember { mutableStateListOf<Person>() }

    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.

    // In addition, we pass a modifier to the LazyColumn composable. You can think of
    // Modifiers as implementations of the decorators pattern that  are used to modify the 
    // composable that its applied to. In this example, we configure the LazyColumn to 
    // occupy the entire available width using Modifier.fillMaxWidth().
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(items = personList,
            itemContent = { index, person ->
                // AnimatedVisibility is a pre-defined composable that automatically animates the 
                // appearace and disappearance of it's content. This makes it super easy to animated 
                // things like insertion/deletion of a list element. The visible property tells the
                // AnimatedVisibility about whether to show the composable that it wraps (in this case, 
                // the Card that you see below). This is where you can add logic about whether a certain 
                // element needs to either be shown or not. In our case, we want to show an element, only
                // if its not a part of the deletedPersonList list. As this list changes and a given 
                // person is either shown or hidden from the screen, the "enter" and "exit" animations 
                // are called for a given item. AnimatedVisibility also let's you specify the enter and 
                // exit animation so that you have full control over how you'd like to animate it's enter
                // or exit. In the example below, since I also added functionality to delete an item, I 
                // customize the exit animation to be an animation that shrinks vertically and gave the 
                // animation a duration of 1000ms. 
                AnimatedVisibility(
                    visible = !deletedPersonList.contains(person),
                    enter = expandVertically(),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 1000,
                        )
                    )
                ) {
                    // Card composable is a predefined composable that is meant to represent the 
                    // card surface as specified by the Material Design specification. We also 
                    // configure it to have rounded corners and apply a modifier.
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = colors[index % colors.size],
                        modifier = Modifier.fillParentMaxWidth()
                    ) {
                        // Row is a composable that places its children in a horizontal sequence. You
                        // can think of it similar to a LinearLayout with the horizontal orientation.
                        Row(
                            modifier = Modifier.fillParentMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
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
                            IconButton(
                                // When this button is clicked, we add the person to deletedPersonList.
                                onClick = {
                                    deletedPersonList.add(person)
                                }
                            ) {
                                // Simple composable that allows you to draw an icon on the screen. It
                                // accepts a vector asset as the icon.
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete"
                                )
                            }
                        }
                    }
                }
            })
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
@ExperimentalAnimationApi
@Preview
@Composable
fun ListAnimationComponentPreview() {
    ListAnimationComponent(getPersonList())
}
