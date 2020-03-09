package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Canvas
import androidx.ui.geometry.Offset
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutSize

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomViewComponent()
        }
    }
}

@Composable
fun CustomViewComponent() {
    val paint = Paint().apply {
        style = PaintingStyle.fill
    }
    Container(modifier = LayoutSize.Fill) {
        Canvas(modifier = Modifier.None) {
            paint.color = Color.Red
            drawCircle(
                center = Offset(size.width.value / 2, size.height.value / 2),
                radius = 300f,
                paint = paint
            )

            paint.color = Color.Green
            drawCircle(
                center = Offset(size.width.value / 2, size.height.value / 2),
                radius = 200f,
                paint = paint
            )

            paint.color = Color.Blue
            drawCircle(
                center = Offset(size.width.value / 2, size.height.value / 2),
                radius = 100f,
                paint = paint
            )
        }
    }
}