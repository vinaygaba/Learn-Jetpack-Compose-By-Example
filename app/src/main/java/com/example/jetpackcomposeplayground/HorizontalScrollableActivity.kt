package com.example.jetpackcomposeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.core.sp
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle

class HorizontalScrollableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalScrollableComponent(getPersonList())
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun HorizontalScrollableComponent(personList: List<Person>) {
    HorizontalScroller {
        Row() {
            for(person in personList) {
                Card(shape = RoundedCornerShape(4.dp), color = Color.Black,
                    modifier = Spacing(16.dp)) {
                    Text(person.name,
                        modifier = Spacing(16.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        ))
                }
            }
        }
    }
}
