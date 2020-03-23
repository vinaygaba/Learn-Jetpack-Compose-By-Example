package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutAspectRatio
import androidx.ui.layout.LayoutOffset
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.core.colors

class LayoutModifierActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerticalScroller {
                Column {
                    SamePaddingComponent()
                    CustomPaddingComponent()
                    OffsetComponent()
                    AspectRatioComponent()
                }
            }
        }
    }
}

@Composable
fun SamePaddingComponent() {
    Surface(color = colors[0]) {
        Text(
            text = "This text has equal padding of 16dp in all directions",
            modifier = LayoutPadding(16.dp),
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun CustomPaddingComponent() {
    Surface(color = colors[1]) {
        Text(
            text = "This text has 32dp start padding, 4dp end padding, 32dp top padding & 0dp " +
                    "bottom padding padding in each direction",
            modifier = LayoutPadding(start = 32.dp, end = 4.dp, top = 32.dp, bottom = 0.dp),
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun OffsetComponent() {
    Surface(color = colors[2], modifier = LayoutOffset(x = 8.dp, y = 8.dp)) {
        Text(
            text = "This text is using an offset of 8 dp instead of padding. Padding also ends up" +
                    " modifying the size of the layout. Using offset instead ensures that the " +
                    "size of the layout retains its size.",
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}

@Composable
fun AspectRatioComponent() {
    Surface(color = colors[3], modifier = LayoutAspectRatio(16/9f) +
            LayoutPadding(top = 16.dp)) {
        Text(
            text = "This text is wrapped in a layout that has a fixed aspect ratio of 16/9",
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif),
            modifier = LayoutPadding(16.dp)
        )
    }
}