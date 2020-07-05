package com.example.jetpackcompose.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.drawLayer
import androidx.ui.core.gesture.DragObserver
import androidx.ui.core.gesture.rawDragGestureFilter
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.gestures.zoomable
import androidx.ui.geometry.Offset
import androidx.ui.layout.fillMaxSize
import androidx.ui.res.loadImageResource
import com.example.jetpackcompose.R

class ZoomableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            ZoomableComposable()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
fun ZoomableComposable() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value scale(for zooming in the image) & translation(for panning across the image). Any 
    // composable that reads the value of counter will be recomposed any time the value changes. 
    // This ensures that only the composables that depend on this will be redraw while the 
    // rest remain unchanged. This ensures efficiency and is a performance optimization. It 
    // is inspired from existing frameworks like React.
    var scale by state { 1f }
    var translate by state { Offset(0f, 0f) }

    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the
    // Box. In the example below, we make the Box composable zoomable by assigning the 
    // Modifier.zoomable modifier & also add a drag observer to it(for panning functionality)
    // by using the rawDragGestureFilter modifier. 
    Box(
        gravity = Alignment.Center,
        modifier = Modifier.zoomable(onZoomDelta = { scale *= it }) + Modifier.rawDragGestureFilter(
            object : DragObserver {
                override fun onDrag(dragDistance: Offset): Offset {
                    translate = translate.plus(dragDistance)
                    return super.onDrag(dragDistance)
                }
            })
    ) {
        // There are multiple methods available to load an image resource in Compose. 
        // However, it would be advisable to use the loadImageResource method as it loads 
        // an image resource asynchronously
        val imageAsset = loadImageResource(id = R.drawable.landscape).resource.resource
        imageAsset?.let {
            // Image is a pre-defined composable that lays out and draws a given [ImageAsset]. 
            // We use the drawLayer modifier to modify the scale & translation of the image. 
            // This is read from the state properties that we created above.
            Image(
                modifier = Modifier.fillMaxSize() + Modifier.drawLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = translate.x,
                    translationY = translate.y
                ),
                asset = it
            )
        }
    }
}

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Composable
fun ZoomableComposablePreview() {
    ZoomableComposable()
}
