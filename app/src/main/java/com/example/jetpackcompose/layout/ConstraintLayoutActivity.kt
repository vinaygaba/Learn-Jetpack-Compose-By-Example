package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Column
import androidx.ui.layout.ConstraintLayout
import androidx.ui.layout.ConstraintSet
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.material.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
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
                    // Title Component is a custom composable that we created which is capable of
                    // rendering text on the screen in a certain font style & text size.
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
    // Ambient is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use Ambients. In this example, we use the
    // ContextAmbient to get hold of the Context object. In order to get access to the latest
    // value of the Ambient, use the "current" property eg - ContextAmbient.current. Some other
    // examples of common Ambient's are TextInputServiceAmbient, DensityAmbient,
    // CoroutineContextAmbient, etc.
    val resources = ContextAmbient.current.resources

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the Card
    // composable to have a padding of 8dp, height of 120 dp & specify it occupy the entire
    // available width.
    Card(modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope. We specify constraints using ConstraintSet
        ConstraintLayout(ConstraintSet {
            // In order to specify constraints, we use tags and assign constraints to the tags.
            // In order to apply these constraints to a composable(view/layout), we reference
            // these tags to impose the respective constraint on that composable. Look at how
            // each of these tags are being reference below using the Modifier.tag modifier.
            val title = tag("title")
            val subtitle = tag("subtitle")
            val image = tag("image")

            // We apply the constraints to tags using ConstraintLayout related helper functions.
            image.apply {
                // We want to vertically center the image tag
                centerVertically()
                // Constraint the left edge of image tag to the left edge of the parent
                left constrainTo parent.left
                // Add a margin of 16 dp to the left edge of image tag
                left.margin = 16.dp
            }

            title.apply {
                // Constraint the left edge of the title to the right edge of the image
                left constrainTo image.right
                // Add a margin of 16 dp to the left edge
                left.margin = 16.dp
                // Constraint the top edge of the title to the top edge of the image
                top constrainTo image.top
            }

            subtitle.apply {
                // Constraint the bottom edge of the subtitle to the bottom edge of the image
                bottom constrainTo image.bottom
                // Constraint the left edge of the subtitle to the right edge of the image
                left constrainTo image.right
                // Add a margin of 16 dp to the left edge
                left.margin = 16.dp
            }

        }) {
            // This is where we specify the children of the ConstraintLayout composable.

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints that we defined above, we make use of the
            // Modifier.tag modifier and use the same key that we passed when creating the tags to
            // reference the appropriate constraint. This way, the corresponding constraints are
            // applied to the composable referencing it.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. Some examples of modifiers are
            // Modifier.tag, Modifier.padding, Modifier.preferredHeight, Modifier.preferredWidth,
            // etc
            Text("Title", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("title"))
            Text("Subtitle", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("subtitle"))
            Box(modifier = Modifier.tag("image") + Modifier.preferredHeight(72.dp) +
                    Modifier.preferredWidth(72.dp)) {
                Image(imageFromResource(resources, R.drawable.lenna))
            }
        }
    }
}

@Composable
fun GuidelineConstraintLayoutComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the Card
    // composable to have a padding of 8dp, height of 120 dp & specify it occupy the entire
    // available width.
    Card(modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() +
            Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope. We specify constraints using ConstraintSet
        ConstraintLayout(constraintSet = ConstraintSet {
            // In order to specify constraints, we use tags and assign constraints to the tags.
            // In order to apply these constraints to a composable(view/layout), we reference
            // these tags to impose the respective constraint on that composable. Look at how
            // each of these tags are being reference below using the Modifier.tag modifier.
            val text1 = tag("quarter")
            val text2 = tag("half")

            // Create a guideline that's placed at a 25% width percentage from the left of the
            // ConstraintLayout. To learn more about guideline, see -
            // https://developer.android.com/reference/android/support/constraint/Guideline
            val quarter = createGuidelineFromLeft(percent = 0.25f)
            // Create a guideline that's placed at a 50% width percentage from the left of the
            // ConstraintLayout
            val half = createGuidelineFromLeft(percent = 0.5f)

            // We apply the constraints to tags using ConstraintLayout related helper functions.
            text1.apply {
                // Constraint the right edge of text1 to the quarter guideline
                right constrainTo quarter
                // We want to vertically center the text1 tag
                centerVertically()
            }

            text2.apply {
                // Constraint the left edge of text2 to the half guideline
                left constrainTo half
                // We want to vertically center the text2 tag
                centerVertically()
            }
        }) {
            // This is where we specify the children of the ConstraintLayout composable.

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints that we defined above, we make use of the
            // Modifier.tag modifier and use the same key that we passed when creating the tags to
            // reference the appropriate constraint. This way, the corresponding constraints are
            // applied to the composable referencing it.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. Some examples of modifiers are
            // Modifier.tag, Modifier.padding, Modifier.preferredHeight, Modifier.preferredWidth,
            // etc
            Text("Quarter", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("quarter"))
            Text("Half", style = TextStyle(fontFamily = FontFamily.Serif, fontWeight =
            FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("half"))
        }
    }
}

@Composable
fun BarrierConstraintLayoutComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the Card
    // composable to have a padding of 8dp, height of 120 dp & specify it occupy the entire
    // available width.
    Card(modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope. We specify constraints using ConstraintSet
        ConstraintLayout(constraintSet = ConstraintSet {
            // In order to specify constraints, we use tags and assign constraints to the tags.
            // In order to apply these constraints to a composable(view/layout), we reference
            // these tags to impose the respective constraint on that composable. Look at how
            // each of these tags are being reference below using the Modifier.tag modifier.
            val text1 = tag("text1")
            val text2 = tag("text2")
            val text3 = tag("text3")

            // We apply the constraints to tags using ConstraintLayout related helper functions.
            text1.apply {
                // Constraint the left edge of the text1 to the left edge of the parent
                left constrainTo parent.left
                // Add a margin of 16 dp to the left edge
                left.margin = 16.dp
                // We want to vertically center the text1 tag
                centerVertically()
            }

            text2.apply {
                // Constraint the left edge of the text2 to the left edge of the parent
                left constrainTo parent.left
                // Add a margin of 16 dp to the left edge
                left.margin = 16.dp
                // Constraint the top edge of the text2 to the bottom edge of text1
                top constrainTo text1.bottom
                // Add a margin of 16 dp to the top edge of text2
                top.margin = 16.dp
                // Add a margin of 16 dp to the bottom edge of text2
                bottom.margin = 16.dp
            }

            // Create a barrier to the right of text1 & text2. To learn more about barriers in
            // constraint layout, see -
            // https://developer.android.com/reference/android/support/constraint/Barrier
            val barrier = createRightBarrier(text1, text2)
            // Add a margin of 16dp to the barrier
            barrier.margin = 16.dp

            text3.apply {
                // Constraint the left edge of the text3 to the barrier we created above
                left constrainTo barrier
                // We want to vertically center the text3 tag
                centerVertically()
            }
        }) {
            // This is where we specify the children of the ConstraintLayout composable.

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints that we defined above, we make use of the
            // Modifier.tag modifier and use the same key that we passed when creating the tags to
            // reference the appropriate constraint. This way, the corresponding constraints are
            // applied to the composable referencing it.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. Some examples of modifiers are
            // Modifier.tag, Modifier.padding, Modifier.preferredHeight, Modifier.preferredWidth,
            // etc
            Text("Short text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("text1"))
            Text("This is a long text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("text2"))
            Text("Barrier Text", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("text3"))
        }
    }
}

@Composable
fun BiasConstraintLayoutComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we configure the Card
    // composable to have a padding of 8dp, height of 120 dp & specify it occupy the entire
    // available width.
    Card(modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope. We specify constraints using ConstraintSet
        ConstraintLayout(constraintSet = ConstraintSet {
            // In order to specify constraints, we use tags and assign constraints to the tags.
            // In order to apply these constraints to a composable(view/layout), we reference
            // these tags to impose the respective constraint on that composable. Look at how
            // each of these tags are being reference below using the Modifier.tag modifier.
            val text1 = tag("text1")
            val text2 = tag("text2")

            // We apply the constraints to tags using ConstraintLayout related helper functions.
            text1.apply {
                // We want to horizontally center the text1 tag
                centerHorizontally()
                // We want to vertically center the text1 tag
                centerVertically()
                // Add a horizontal bias of 0.1g to text1. To learn more about bias, see -
                // https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Bias
                horizontalBias = 0.1f
            }

            text2.apply {
                // We want to horizontally center the text1 tag
                centerHorizontally()
                // We want to vertically center the text1 tag
                centerVertically()
                // Add a horizontal bias of 0.9 to text2. To learn more about bias, see -
                // https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Bias
                horizontalBias = 0.9f
            }
        }) {
            // This is where we specify the children of the ConstraintLayout composable.

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints that we defined above, we make use of the
            // Modifier.tag modifier and use the same key that we passed when creating the tags to
            // reference the appropriate constraint. This way, the corresponding constraints are
            // applied to the composable referencing it.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. Some examples of modifiers are
            // Modifier.tag, Modifier.padding, Modifier.preferredHeight, Modifier.preferredWidth,
            // etc
            Text("Left", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("text1"))
            Text("Right", style = TextStyle(fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = Modifier.tag("text2"))
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
@Preview("Simple constraint layout example")
@Composable
fun SimpleConstraintLayoutComponentPreview() {
    Column {
        SimpleConstraintLayoutComponent()
    }
}

@Preview("Constraint layout example with guidelines")
@Composable
fun GuidelineConstraintLayoutComponentPreview() {
    Column {
        GuidelineConstraintLayoutComponent()
    }
}

@Preview("Constraint layout example with barrier")
@Composable
fun BarrierConstraintLayoutComponentPreview() {
    Column {
        BarrierConstraintLayoutComponent()
    }
}

@Preview("Constraint layout example with bias")
@Composable
fun BiasConstraintLayoutComponentPreview() {
    Column {
        BiasConstraintLayoutComponent()
    }
}

