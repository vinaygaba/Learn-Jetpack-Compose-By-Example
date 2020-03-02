package com.example.jetpackcomposeplayground.material

import android.os.Bundle
import android.util.Log
import androidx.animation.TweenBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.CutCornerShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Menu
import androidx.ui.material.icons.outlined.Favorite
import androidx.ui.material.ripple.Ripple
import androidx.ui.unit.dp
import kotlin.math.abs
import kotlin.math.roundToInt

class BottomAppBarActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldWithBottomBarAndCutout()
        }
    }
}

private val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    val scaffoldState = remember { ScaffoldState() }
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val sharpEdgePercent = -50f
    val roundEdgePercent = 45f
    // Start with sharp edges
    val animatedProgress = animatedFloat(sharpEdgePercent)
    // animation value to animate shape
    val progress = animatedProgress.value.roundToInt()
    Log.e("Progress", "Progress $progress")
    // When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
    // This allows for a smooth transition between cut corners and round corners.
    val fabShape = if (progress < 0) {
        CutCornerShape(abs(progress))
    } else if (progress == roundEdgePercent.toInt()) {
        CircleShape
    } else {
        RoundedCornerShape(progress)
    }
    // lambda to call to trigger shape animation
    val changeShape = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
        animatedProgress.animateTo(
            targetValue = nextTarget,
            anim = TweenBuilder<Float>().apply { duration = 600 }
        )
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topAppBar = { TopAppBar(title = { Text("Scaffold with bottom cutout") }) },
        bottomAppBar = { fabConfiguration ->
            BottomAppBar(fabConfiguration = fabConfiguration, cutoutShape = fabShape) { _: String ->
                IconButton(onClick = {
                    scaffoldState.drawerState = DrawerState.Opened
                }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = changeShape,
                shape = fabShape,
                color = MaterialTheme.colors().secondary
            ) {
                Icon(Icons.Outlined.Favorite)
//                Text("Change shape", modifier = LayoutPadding(12.dp))
            }
        },
        floatingActionButtonPosition = Scaffold.FabPosition.EndDocked,
        bodyContent = { modifier ->
            VerticalScroller {
                Column(modifier) {
                    repeat(100) {
                        ColoredRect(color = colors[it % colors.size], height = 50.dp)
                    }
                }
            }
        }
    )
}

@Composable
fun Icon(
    icon: VectorAsset,
    modifier: Modifier = Modifier.None,
    tint: Color = contentColor()
) {
    // TODO: consider allowing developers to override the intrinsic size, and specify their own
    // size that this icon will be forced to take up.
    // TODO: b/149735981 semantics for content description
    Box(modifier + LayoutWidth(icon.defaultWidth) + LayoutHeight(icon.defaultHeight)) {
        DrawVector(vectorImage = icon, tintColor = tint)
    }
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.None,
    children: @Composable() () -> Unit
) {
    Ripple(bounded = false, radius = 24.dp) {
        Clickable(onClick) {
            Box(
                modifier = modifier + LayoutSize(48.dp),
                gravity = ContentGravity.Center,
                children = children
            )
        }
    }
}