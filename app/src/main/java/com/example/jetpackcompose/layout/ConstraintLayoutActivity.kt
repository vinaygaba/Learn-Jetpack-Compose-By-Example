package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.ScrollableColumn
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Column
import androidx.ui.layout.ConstraintLayout
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

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // ScrollableColumn is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            ScrollableColumn {
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
    Card(
        modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope.
        ConstraintLayout {
            // This is where we specify the children of the ConstraintLayout composable.

            // In order to specify constraints, we use the helper function called createRefs. 
            // This function helps us create ConstrainedLayoutReference, which we will assign to 
            // our composable layouts. In order to apply these constraints to a 
            // composable(view/layout), we reference these references to impose the respective 
            // constraint on that composable. Look at how each of these references are being 
            // reference below using the Modifier.contrainAs modifier.
            val (title, subtitle, image) = createRefs()

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using
            // the style property. We also pass a modifier to it.

            // You can think of Modifiers as implementations of the decorators pattern that are used 
            // to modify the composable that its applied to. In the example below, we configure the
            // Box to occupy the entire available height & width using Modifier.fillMaxSize().
            Text(
                "Title", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(title) {
                    // Constraint the left edge of title to the right edge of the image 
                    // and add a margin of 16dp
                    start.linkTo(image.end, margin = 16.dp)
                    // Constraint the top edge of title to the top edge of the image 
                    top.linkTo(image.top)
                }
            )
            Text(
                "Subtitle", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(subtitle) {
                    // Constraint the bottom edge of subtitle to the bottom edge of the image 
                    bottom.linkTo(image.bottom)
                    // Constraint the start/left edge of subtitle to the right/end edge of the 
                    // image and add a margin on 16.dp
                    start.linkTo(image.end, margin = 16.dp)
                }
            )
            // Box is a predefined convenience composable that allows you to apply common draw & layout
            // logic. We give it a ContentGravity of Center to ensure the children of this composable
            // are placed in its center. 
            Box(
                modifier = Modifier.preferredHeight(72.dp) +
                        Modifier.preferredWidth(72.dp) +
                        Modifier.constrainAs(image) {
                            // We want to vertically center the image tag
                            centerVerticallyTo(parent)
                            // Constraint the left edge of image to the left edge of the parent 
                            // and add a margin of 16dp
                            start.linkTo(parent.start, margin = 16.dp)
                        }
            ) {
                // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
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
    Card(
        modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() +
                Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope.
        ConstraintLayout {
            // This is where we specify the children of the ConstraintLayout composable.

            // In order to specify constraints, we use the helper function called createRefs. 
            // This function helps us create ConstrainedLayoutReference, which we will assign to 
            // our composable layouts. In order to apply these constraints to a 
            // composable(view/layout), we reference these references to impose the respective 
            // constraint on that composable. Look at how each of these references are being 
            // reference below using the Modifier.contrainAs modifier.
            val (text1, text2) = createRefs()

            // Create a guideline that's placed at a 25% width percentage from the left of the
            // ConstraintLayout. To learn more about guideline, see -
            // https://developer.android.com/reference/android/support/constraint/Guideline
            val quarter = createGuidelineFromStart(0.25f)

            // Create a guideline that's placed at a 50% width percentage from the left of the
            // ConstraintLayout
            val half = createGuidelineFromStart(0.5f)

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints to the references that we created above, we make 
            // use of the Modifier.constrainAs modifier and pass the reference to it in order to 
            // create a mapping between the composable/layout and the reference. We then add 
            // contraints to the references inside the lambda passed to the constrainAs modifier.
            Text(
                "Quarter", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text1) {
                    // We want to vertically center text1
                    centerVerticallyTo(parent)
                    // Constraint the right edge of text1 to the quarter guideline
                    end.linkTo(quarter)
                }
            )
            Text(
                "Half", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text2) {
                    // We want to vertically center text2
                    centerVerticallyTo(parent)
                    // Constraint the left edge of text2 to the half guideline
                    start.linkTo(half)
                }
            )
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
    Card(
        modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope.
        ConstraintLayout {
            // This is where we specify the children of the ConstraintLayout composable.

            // In order to specify constraints, we use the helper function called createRefs. 
            // This function helps us create ConstrainedLayoutReference, which we will assign to 
            // our composable layouts. In order to apply these constraints to a 
            // composable(view/layout), we reference these references to impose the respective 
            // constraint on that composable. Look at how each of these references are being 
            // reference below using the Modifier.contrainAs modifier.
            val (text1, text2, text3) = createRefs()


            // Create a barrier to the right of text1 & text2. To learn more about barriers in
            // constraint layout, see -
            // https://developer.android.com/reference/android/support/constraint/Barrier
            // Also added a margin of 16dp to the barrier
            val barrier = createEndBarrier(text1, text2, margin = 16.dp)

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints to the references that we created above, we make 
            // use of the Modifier.constrainAs modifier and pass the reference to it in order to 
            // create a mapping between the composable/layout and the reference. We then add 
            // contraints to the references inside the lambda passed to the constrainAs modifier.
            Text(
                "Short text", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text1) {
                    // Constraint the left edge of the text1 to the left edge of the parent
                    // and add a margin of 16 dp to the left edge
                    start.linkTo(parent.start, margin = 16.dp)
                    // We want to vertically center text1
                    centerVerticallyTo(parent)
                }
            )
            Text(
                "This is a long text", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text2) {
                    // Constraint the left edge of the text2 to the left edge of the parent
                    // and added a margin of 16 dp to the left edge
                    start.linkTo(parent.start, margin = 16.dp)

                    // Constraint the top edge of the text2 to the bottom edge of text1
                    top.linkTo(text1.bottom, margin = 16.dp)

                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
            )
            Text(
                "Barrier Text", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text3) {
                    // Constraint the left edge of the text3 to the barrier we created above
                    start.linkTo(barrier)
                    // We want to vertically center the text3
                    centerVerticallyTo(parent)
                }
            )
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
    Card(
        modifier = Modifier.preferredHeight(120.dp) + Modifier.fillMaxWidth() + Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        // ConstraintLayout is a composable that positions its children based on the constraints
        // we specify in its scope. 
        ConstraintLayout {
            // This is where we specify the children of the ConstraintLayout composable.

            // In order to specify constraints, we use the helper function called createRefs. 
            // This function helps us create ConstrainedLayoutReference, which we will assign to 
            // our composable layouts. In order to apply these constraints to a 
            // composable(view/layout), we reference these references to impose the respective 
            // constraint on that composable. Look at how each of these references are being 
            // reference below using the Modifier.contrainAs modifier.
            val (text1, text2) = createRefs()

            // Text is a predefined composable that does exactly what you'd expect it to -
            // display text on the screen. It allows you to customize its appearance using the
            // style property.

            // In order to apply the constraints to the references that we created above, we make 
            // use of the Modifier.constrainAs modifier and pass the reference to it in order to 
            // create a mapping between the composable/layout and the reference. We then add 
            // contraints to the references inside the lambda passed to the constrainAs modifier.
            Text(
                "Left", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text1) {
                    // We want to vertically center the text1 
                    centerVerticallyTo(parent)
                    // Add a horizontal bias of 0.1g to text1. To learn more about bias, see -
                    // https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Bias
                    linkTo(parent.start, parent.end, bias = 0.1f)
                }
            )
            Text(
                "Right", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(text2) {
                    // We want to horizontally center the text1
                    centerHorizontallyTo(parent)
                    // We want to vertically center the text1
                    centerVerticallyTo(parent)
                    // Add a horizontal bias of 0.9 to text2. To learn more about bias, see -
                    // https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Bias
                    linkTo(parent.start, parent.end, bias = 0.9f)
                }
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

