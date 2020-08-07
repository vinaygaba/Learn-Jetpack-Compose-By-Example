package com.example.jetpackcompose.state.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoroutineFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            FlowComponent(countdownFlow())
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun FlowComponent(flow: Flow<Int>) {
    // Here we access the coroutine flow object and convert it to a form that Jetpack Compose 
    // understands using the collectAsState method. 

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val countDownValue by flow.collectAsState(initial = 10)
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to occupy the entire available width and height using Modifier.fillMaxSize
    // (). We also configure the content inside this box to have the center gravity.
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        when (countDownValue) {
            // When the value is between 1 to 10, show the countDownValue 
            in 1..10 -> {
                CountdownText("Countdown: $countDownValue")
            }
            // Else it's already new year, so its time to wish your friends! 
            else -> {
                CountdownText("HAPPY NEW YEAR!!!!", Color.Magenta)
            }
        }
    }

}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun CountdownText(text: String, color: Color = Color.Black) {
    // Text is a predefined composable that does exactly what you'd expect it to -
    // display text on the screen. It allows you to customize its appearance using the
    // style property.
    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    )
}

// This method is simply creating a coroutine flow that counts down from 9 to 0. The value is 
// updated after every 1 second. If you aren't familiar with coroutine flows, they are coroutine's
// version of RxJava's observables/flowables. If you aren't familiar with RxJava either, think of
// them as an open stream that notifies its observers when the value changes. 
fun countdownFlow() = flow<Int> {
    for (i in 9 downTo 0) {
        delay(1000L)
        emit(i)
    }
}

