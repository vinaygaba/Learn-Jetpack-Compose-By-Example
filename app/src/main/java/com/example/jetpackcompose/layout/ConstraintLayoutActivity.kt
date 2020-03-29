package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.SimpleImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.layout.constraintlayout.ConstraintLayout
import androidx.ui.layout.constraintlayout.ConstraintSet
import androidx.ui.material.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.image.TitleComponent

class ConstraintLayoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // Vertical scroller is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            VerticalScroller {
                // Column is a composable that places its children in a vertical sequence.
                Column {
                    TitleComponent("Simple constraint layout example")
                    SimpleConstraintLayoutComponent()

                    TitleComponent("Constraint layout example with guidelines")
                    GuidelineConstraintLayoutComponent()

                    TitleComponent("Constraint layout example with barriers")
                    BarrierConstraintLayoutComponent()

                    TitleComponent("Constraint layout example with bias")
                    BiasConstraintLayoutComponent()
                }
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun SimpleConstraintLayoutComponent() {
    val resources = ContextAmbient.current.resources
    Card(modifier = LayoutHeight(120.dp) + LayoutWidth.Fill + LayoutPadding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        ConstraintLayout(ConstraintSet {
            val title = tag("title")
            val subtitle = tag("subtitle")
            val image = tag("image")

            image.apply {
                centerVertically()
                left constrainTo parent.left
                left.margin = 16.dp
            }

            title.apply {
                left constrainTo image.right
                left.margin = 16.dp
                top constrainTo image.top
            }

            subtitle.apply {
                bottom constrainTo image.bottom
                left constrainTo image.right
                left.margin = 16.dp
            }

        }) {

            Text("Title", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("title"))
            Text("Subtitle", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("subtitle"))
            Container(width = 72.dp, height = 72.dp, modifier = LayoutTag("image")) {
                SimpleImage(imageFromResource(resources, R.drawable.lenna))
            }
        }
    }
}

@Composable
fun GuidelineConstraintLayoutComponent() {
    Card(modifier = LayoutHeight(120.dp) + LayoutWidth.Fill + LayoutPadding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        ConstraintLayout(constraintSet = ConstraintSet {
            val text1 = tag("quarter")
            val text2 = tag("half")

            val quarter = createGuidelineFromLeft(percent = 0.25f)
            val half = createGuidelineFromLeft(percent = 0.5f)

            text1.apply {
                right constrainTo quarter
                centerVertically()
            }

            text2.apply {
                left constrainTo half
                centerVertically()
            }
        }) {
            Text("Quarter", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("quarter"))
            Text("Half", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("half"))
        }
    }
}

@Composable
fun BarrierConstraintLayoutComponent() {
    Card(modifier = LayoutHeight(120.dp) + LayoutWidth.Fill + LayoutPadding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        ConstraintLayout(constraintSet = ConstraintSet {
            val text1 = tag("text1")
            val text2 = tag("text2")
            val text3 = tag("text3")

            text1.apply {
                left constrainTo parent.left
                left.margin = 16.dp
                centerVertically()
            }

            text2.apply {
                left constrainTo parent.left
                left.margin = 16.dp
                top constrainTo text1.bottom
                top.margin = 16.dp
                bottom.margin = 16.dp
            }

            val barrier = createRightBarrier(text1, text2)
            barrier.margin = 16.dp
            text3.apply {
                left constrainTo barrier
                centerVertically()
            }
        }) {
            Text("Short text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("text1"))
            Text("This is a long text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("text2"))
            Text("Barrier Text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("text3"))
        }
    }
}

@Composable
fun BiasConstraintLayoutComponent() {
    Card(modifier = LayoutHeight(120.dp) + LayoutWidth.Fill + LayoutPadding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        ConstraintLayout(constraintSet = ConstraintSet {
            val text1 = tag("text1")
            val text2 = tag("text2")

            text1.apply {
                centerHorizontally()
                centerVertically()
                horizontalBias = 0.1f
            }

            text2.apply {
                centerHorizontally()
                centerVertically()
                horizontalBias = 0.9f
            }
        }) {
            Text("Left", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("text1"))
            Text("Right", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutTag("text2"))
        }
    }
}

