package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.gesture.PressIndicatorGestureDetector
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.graphics.Path
import androidx.ui.graphics.StrokeJoin
import androidx.ui.layout.fillMaxSize

class CustomViewPainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            CustomDrawableViewComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun CustomDrawableViewComponent() {
    val paint = Paint().apply {
        color = Color.Black
        style = PaintingStyle.stroke
        strokeJoin = StrokeJoin.round
    }

    val paths by state<MutableList<Paths>> { mutableListOf() }
//    DrawingBoardComposable(paths, paint)
}

data class Paths(
    val x: Float,
    val y: Float
)

//@Composable
//fun DrawingBoardComposable(paths: List<Paths>, paint: Paint) {
//    Box(modifier = Modifier.fillMaxSize() + PressIndicatorGestureDetector(onStart = {
//        paths = paths + Paths(it.x.value, it.y.value)
//    }, onStop = {})) {
//        Canvas(modifier = Modifier.None) {
//            val p = Path()
//            for (path in paths) {
//                p.lineTo(path.x, path.y)
//                p.moveTo(path.x, path.y)
//            }
//            drawPath(p, paint)
//        }
//    }
//}