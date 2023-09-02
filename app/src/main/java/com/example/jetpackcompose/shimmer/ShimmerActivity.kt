package com.example.jetpackcompose.shimmer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
                    item(
                        color = lightRed,
                        tweenType = FastOutSlowInEasing,
                    )

                    spacerHeight(height = 8.dp)

                    item(
                        color = lightPurple,
                        tweenType = LinearOutSlowInEasing
                    )

                    spacerHeight(height = 8.dp)


                    item(
                        color = lightBlue,
                        tweenType = FastOutLinearInEasing
                    )

                    spacerHeight(height = 8.dp)


                    item(
                        color = lightCyan,
                        tweenType = LinearEasing
                    )

                    spacerHeight(height = 8.dp)


                    item(
                        color = lightTeal,
                        tweenType = LinearOutSlowInEasing
                    )
                }
            }
        }
    }

    @Composable
    private fun item(color: Color, tweenType: Easing) {
        ImagePlaceHolder(
            color = color,
            tweenType = tweenType,
            size = 100.dp
        )
        spacerHeight(height = 4.dp)
        LinePlaceHolder(
            color = color,
            tweenType = tweenType, width = 1f
        )
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
fun spacerHeight(height: Dp) = Spacer(modifier = Modifier.height(height))

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
