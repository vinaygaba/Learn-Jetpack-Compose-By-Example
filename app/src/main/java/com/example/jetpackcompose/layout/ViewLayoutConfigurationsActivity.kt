package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.text.FirstBaseline
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class ViewLayoutConfigurationsActivity: AppCompatActivity() {
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
                    TitleComponent("Child views with equal weights")
                    RowEqualWeightComponent()

                    TitleComponent("Child views with unequal weights")
                    RowUnequalWeightComponent()

                    TitleComponent("Child view with auto space in between")
                    RowAddSpaceBetweenViewsComponent()

                    TitleComponent("Child views spaced evenly")
                    RowSpaceViewsEvenlyComponent()

                    TitleComponent("Space added around child views")
                    RowSpaceAroundViewsComponent()

                    TitleComponent("Child views centered")
                    RowViewsCenteredComponent()

                    TitleComponent("Child views arranged in end")
                    RowViewsArrangedInEndComponent()

                    TitleComponent("Baseline of child views aligned")
                    RowBaselineAlignComponent()

                    TitleComponent("Baseline of child views not aligned")
                    RowBaselineUnalignedComponent()
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
fun RowEqualWeightComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        // This Row consists of two buttons. We wanted to ensure that both these buttons occupy
        // equal amount of width. We do that by using the Modifier.weight modifier and passing equal
        // weight to both the buttons. This is similar to how we used layout_weight with
        // LinearLayouts in the old Android UI Toolkit.
        Button(modifier = Modifier.weight(1f) + Modifier.padding(4.dp), onClick = {}) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(modifier = Modifier.weight(1f) + Modifier.padding(4.dp), onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowUnequalWeightComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        // This Row consists of two buttons. We wanted to ensure that both the first button
        // occupies 2/3rd of the screen and the other button occupies the remaining 1/3rd.
        // We do this by using the Modifier.weight modifier and passing equal weight to both the
        // buttons. This is similar to how we used layout_weight with LinearLayouts in the old
        // Android UI Toolkit.
        Button(modifier = Modifier.weight(0.66f) + Modifier.padding(4.dp), onClick = {}) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(modifier = Modifier.weight(0.34f) + Modifier.padding(4.dp), onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowAddSpaceBetweenViewsComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // We use Arrangement.SpaceBetween to place the children of the row such that they are spaced
    // evenly across the main axis, without free space before the first child or after the last child.
    Row(modifier = Modifier.fillMaxWidth() + Modifier.padding(4.dp), horizontalArrangement = Arrangement
        .SpaceBetween) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(onClick = {}) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowSpaceViewsEvenlyComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // We use Arrangement.SpaceEvenly to place the children of the row such that they are
    // spaced evenly across the main axis, including free space before the first child and after
    // the last child.
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(onClick = {}) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowSpaceAroundViewsComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // We use Arrangement.SpaceAround to place the children of the row such that they are spaced
    // evenly across the main axis, including free space before the first child and after the
    // last child, but half the amount of space existing otherwise between two consecutive children.
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(onClick = {}) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowViewsCenteredComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // We use Arrangement.Center to place the children of the row such that they are as close as
    // possible to the middle of the main axis.
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowViewsArrangedInEndComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // We use Arrangement.End to place the children of the row such that they are as close as
    // possible to the end of the main axis.
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.
        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }

        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Sp(20)))
        }
    }
}

@Composable
fun RowBaselineAlignComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        // In order to align the baseline of both the text composables, we use the
        // Modifier.alignWithSiblings(FirstBaseline) modifier. FirstBaseline here means that we
        // align the baseline of the first line of the Text Composable.
        Text(text = "Text 1",
            style = TextStyle(fontSize = TextUnit.Sp(20), fontStyle = FontStyle.Italic),
            modifier = Modifier.alignWithSiblings(alignmentLine = FirstBaseline)
        )
        Text(text = "Text 2",
            style = TextStyle(fontSize = TextUnit.Sp(40), fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold),
            modifier = Modifier.alignWithSiblings(alignmentLine = FirstBaseline)
        )
    }
}

@Composable
fun RowBaselineUnalignedComponent() {
    // Row is a composable that places its children in a horizontal sequence. You can think of it
    // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
    // to the Row composable. You can think of Modifiers as implementations of the decorators
    // pattern that are used to modify the composable that its applied to. In this example, we
    // assign add a modifier to the Row and ask it to extend the full width available to it.
    // Alternatively, we could've assigned a fixed width to this row using
    // Modifier.preferredWidth(val width: Dp).
    Row(modifier = Modifier.fillMaxWidth()) {
        // The Button composable allows you to provide child composables that inherit this button
        // functionality.
        // The Text composable is pre-defined by the Compose UI library; you can use this
        // composable to render text on the screen
        Text(text = "Text 1",
            style = TextStyle(fontSize = TextUnit.Sp(20), fontStyle = FontStyle.Italic))
        Text(text = "Text 2",
            style = TextStyle(fontSize = TextUnit.Sp(40), fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold))
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
@Preview("Child views with equal weights")
@Composable
fun RowEqualWeightComponentPreview() {
    RowEqualWeightComponent()
}

@Preview("Child views with unequal weights")
@Composable
fun RowUnequalWeightComponentPreview() {
    RowUnequalWeightComponent()
}

@Preview("Child view with auto space in between arrangement")
@Composable
fun RowAddSpaceBetweenViewsComponentPreview() {
    RowAddSpaceBetweenViewsComponent()
}

@Preview("Child views spaced evenly arrangement")
@Composable
fun RowSpaceViewsEvenlyComponentPreview() {
    RowSpaceViewsEvenlyComponent()
}

@Preview("Space added around child views arrangement")
@Composable
fun RowSpaceAroundViewsComponentPreview() {
    RowSpaceAroundViewsComponent()
}

@Preview("Child views centered arrangement")
@Composable
fun RowViewsCenteredComponentPreview() {
    RowViewsCenteredComponent()
}

@Preview("Child views arranged in end")
@Composable
fun RowViewsArrangedInEndComponentPreview() {
    RowViewsArrangedInEndComponent()
}

@Preview("Baseline of child views aligned")
@Composable
fun RowBaselineAlignComponentPreview() {
    RowBaselineAlignComponent()
}

@Preview("Baseline of child views not aligned")
@Composable
fun RowBaselineUnalignedComponentPreview() {
    RowBaselineUnalignedComponent()
}
