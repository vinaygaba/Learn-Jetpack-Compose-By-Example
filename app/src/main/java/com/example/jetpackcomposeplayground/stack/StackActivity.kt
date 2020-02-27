package com.example.jetpackcomposeplayground.stack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.image.LocalResourceImageComponent

class StackActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StackComponent()
        }
    }
}

@Composable
fun StackComponent() {
    Stack(modifier = LayoutWidth.Fill + LayoutHeight(200.dp)) {
        LocalResourceImageComponent(resId = R.drawable.lenna)
        Surface(color = Color.Gray) {
            Text("Title", style = TextStyle(fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutPadding(16.dp)
                    + LayoutWidth.Fill
            )
        }
    }
}