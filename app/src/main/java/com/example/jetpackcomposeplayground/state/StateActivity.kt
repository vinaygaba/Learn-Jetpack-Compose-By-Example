package com.example.jetpackcomposeplayground.state

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
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class StateActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = LayoutHeight.Fill) {
                StateComponent()
                ModelComponent()
            }

        }
    }
}

@Composable
fun StateComponent() {
    var counter by state { 0 }
    Text(text = "Example using State", modifier = LayoutPadding(16.dp), style = TextStyle(
        fontSize = 20.sp
    ))
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceEvenly) {
        Button(
            modifier = LayoutPadding(16.dp),
            elevation = 5.dp,
            onClick = {
                counter++
            }) {
            Text(text = "Increment Counter", modifier = LayoutPadding(16.dp))
        }

        Button(
            modifier = LayoutPadding(16.dp),
            elevation = 5.dp,
            onClick = {
                counter = 0
            }) {
            Text(text = "Reset Counter", modifier = LayoutPadding(16.dp))
        }
    }

    Text(text = "Counter value is $counter", modifier = LayoutPadding(16.dp))
    Divider()
}

@Composable
fun ModelComponent(counterState: CounterState = CounterState()) {
    Text(
        text = "Example using Model", modifier = LayoutPadding(16.dp), style = TextStyle(
            fontSize = 20.sp
        )
    )
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceEvenly) {
        Button(
            modifier = LayoutPadding(16.dp),
            elevation = 5.dp,
            onClick = {
                counterState.counter++
            }) {
            Text(text = "Increment Counter", modifier = LayoutPadding(16.dp))
        }

        Button(
            modifier = LayoutPadding(16.dp),
            elevation = 5.dp,
            onClick = {
                counterState.counter = 0
            }) {
            Text(text = "Reset Counter", modifier = LayoutPadding(16.dp))
        }
    }

    Text(text = "Counter value is ${counterState.counter}", modifier = LayoutPadding(16.dp))
}

@Model
class CounterState(var counter: Int = 0)