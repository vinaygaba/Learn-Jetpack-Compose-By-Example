package com.example.jetpackcomposeplayground.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Draw
import androidx.ui.core.setContent
import androidx.ui.geometry.Offset
import androidx.ui.graphics.*
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
        Draw { canvas, parentSize ->
            paint.color = Color.Red
            canvas.drawCircle(
                center = Offset(parentSize.width.value / 2, parentSize.height.value / 2),
                radius = 300f,
                paint = paint
            )

            paint.color = Color.Green
            canvas.drawCircle(
                center = Offset(parentSize.width.value / 2, parentSize.height.value / 2),
                radius = 200f,
                paint = paint
            )

            paint.color = Color.Blue
            canvas.drawCircle(
                center = Offset(parentSize.width.value / 2, parentSize.height.value / 2),
                radius = 100f,
                paint = paint
            )
        }
    }
}