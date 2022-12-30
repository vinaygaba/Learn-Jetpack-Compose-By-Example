package com.example.jetpackcompose.shimmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ShimmerActivity : AppCompatActivity() {

    private val lightRed = Color(0xFFFFCDD2)
    private val lightPurple = Color(0xFFE1BEE7)
    private val lightBlue = Color(0xFFBBDEFB)
    private val lightCyan = Color(0xFFB2EBF2)
    private val lightTeal = Color(0xFFB2DFDB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                item {
                    ImagePlaceHolder(
                        color = lightRed,
                        tweenType = FastOutSlowInEasing,
                        size = 100.dp
                    )
                    spacerHeight(height = 4.dp)
                    LinePlaceHolder(
                        color = lightRed,
                        tweenType = FastOutSlowInEasing, width = 1f
                    )

                    spacerHeight(height = 8.dp)

                    ImagePlaceHolder(
                        color = lightPurple,
                        tweenType = LinearOutSlowInEasing,
                        size = 100.dp
                    )
                    spacerHeight(height = 4.dp)
                    LinePlaceHolder(
                        color = lightPurple,
                        tweenType = LinearOutSlowInEasing,
                        width = 1f
                    )

                    spacerHeight(height = 8.dp)

                    ImagePlaceHolder(
                        color = lightBlue,
                        tweenType = FastOutLinearInEasing,
                        size = 100.dp
                    )
                    spacerHeight(height = 4.dp)
                    LinePlaceHolder(
                        color = lightBlue,
                        tweenType = FastOutLinearInEasing,
                        width = 1f
                    )

                    spacerHeight(height = 8.dp)

                    ImagePlaceHolder(
                        color = lightCyan,
                        tweenType = LinearEasing,
                        size = 100.dp
                    )
                    spacerHeight(height = 4.dp)
                    LinePlaceHolder(
                        color = lightCyan,
                        tweenType = LinearEasing,
                        width = 1f
                    )

                    spacerHeight(height = 8.dp)

                    ImagePlaceHolder(
                        color = lightTeal,
                        tweenType = LinearOutSlowInEasing,
                        size = 100.dp
                    )
                    spacerHeight(height = 4.dp)
                    LinePlaceHolder(
                        color = lightTeal,
                        tweenType = LinearOutSlowInEasing,
                        width = 1f
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePlaceHolder(
    color: Color = Color.LightGray,
    tweenType: Easing = FastOutSlowInEasing,
    size: Dp = 100.dp
) = ShimmerAnimation(
    color = color,
    tweenType = tweenType
) {
    Spacer(
        modifier = Modifier
            .size(size)
            .background(
                brush = it,
            )
    )
}

@Composable
fun LinePlaceHolder(
    color: Color = Color.LightGray,
    tweenType: Easing = FastOutSlowInEasing,
    width: Float = 0.5f
) = ShimmerAnimation(
    color = color,
    tweenType = tweenType
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth(width)
            .height(16.dp)
            .background(
                brush = it,
            )
    )
}

@Composable
fun ShimmerAnimation(
    color: Color = Color.LightGray,
    tweenType: Easing = FastOutSlowInEasing,
    content: @Composable (Brush) -> Unit
) {

    /*
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
    */

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
        Specify animation positions,
        initial Values 0F means it starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(

            /*
             Tween Animates between values over specified [durationMillis]
            */
            tween(durationMillis = 1200, easing = tweenType),
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end= Animate the end position to give the shimmer effect using the transition created above
    */

    val shimmerColorShades = listOf(

        color.copy(0.9f),

        color.copy(0.2f),

        color.copy(0.9f)

    )
    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    content(brush)
}

@Composable
fun spacerHeight(height: Dp) = Spacer(modifier = Modifier.height(height))