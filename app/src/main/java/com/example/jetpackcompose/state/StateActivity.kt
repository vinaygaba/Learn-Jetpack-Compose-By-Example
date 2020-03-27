package com.example.jetpackcompose.state

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class StateActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            Column(modifier = LayoutHeight.Fill) {
                StateComponent()
                Divider()
                ModelComponent()
            }
        }
    }
}

@Composable
fun StateComponent() {
    var counter by state { 0 }
    TitleComponent("Example using state class to store state")
    Row(modifier = LayoutWidth.Fill) {
        Button(
            modifier = LayoutPadding(16.dp) + LayoutWeight(1f),
            elevation = 5.dp,
            onClick = {
                counter++
            }) {
            Text(text = "Increment", modifier = LayoutPadding(16.dp))
        }

        Button(
            modifier = LayoutPadding(16.dp) + LayoutWeight(1f),
            elevation = 5.dp,
            onClick = {
                counter = 0
            }) {
            Text(text = "Reset", modifier = LayoutPadding(16.dp))
        }
    }

    Text(text = "Counter value is $counter", modifier = LayoutPadding(16.dp))
}

@Composable
fun ModelComponent(counterState: CounterState = CounterState()) {
    TitleComponent("Example using Model class to store state")
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceEvenly) {
        Button(
            modifier = LayoutPadding(16.dp) + LayoutWeight(1f),
            elevation = 5.dp,
            onClick = {
                counterState.counter++
            }) {
            Text(text = "Increment", modifier = LayoutPadding(16.dp))
        }

        Button(
            modifier = LayoutPadding(16.dp) + LayoutWeight(1f),
            elevation = 5.dp,
            onClick = {
                counterState.counter = 0
            }) {
            Text(text = "Reset", modifier = LayoutPadding(16.dp))
        }
    }

    Text(text = "Counter value is ${counterState.counter}", modifier = LayoutPadding(16.dp))
}

@Model
class CounterState(var counter: Int = 0)