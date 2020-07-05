package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import androidx.compose.getValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.gesture.DragObserver
import androidx.ui.core.gesture.dragGestureFilter
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.geometry.Offset
import androidx.ui.graphics.Color
import androidx.ui.graphics.Path
import androidx.ui.graphics.StrokeJoin
import androidx.ui.graphics.drawscope.Stroke
import androidx.ui.layout.fillMaxSize

/**
 * This example needs some more work.
 */
class CustomViewPaintActivity: AppCompatActivity() {
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
    DrawingBoardComposable()
}

data class Paths(
    val x: Float,
    val y: Float
)

@Composable
fun DrawingBoardComposable() {
    val paths by state<ModelList<Paths>> { modelListOf() }
    Box(modifier = Modifier.fillMaxSize() + Modifier.dragGestureFilter(
        startDragImmediately = true,
        dragObserver = object: DragObserver {
            override fun onStart(downPosition: Offset) {
                super.onStart(downPosition)
                paths += Paths(downPosition.x, downPosition.y)
            }
        })) {
        Canvas(modifier = Modifier) {
            val p = Path()
            for (path in paths) {
                p.lineTo(path.x, path.y)
                p.moveTo(path.x, path.y)
            }
            drawPath(p, color = Color.Black, style = Stroke(width = 3f, join = StrokeJoin.round))
        }
    }
}
