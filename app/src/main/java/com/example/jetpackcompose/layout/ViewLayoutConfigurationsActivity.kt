package com.example.jetpackcompose.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.FirstBaseline
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Button
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
            VerticalScroller {
                Column {
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
// functions can only be called from within the scope of other composable functions.
@Composable
fun RowEqualWeightComponent() {
    Row(modifier = LayoutWidth.Fill) {
        Button(modifier = LayoutWeight(1f) + LayoutPadding(4.dp), onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(modifier = LayoutWeight(1f) + LayoutPadding(4.dp), onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowUnequalWeightComponent() {
    Row(modifier = LayoutWidth.Fill) {
        Button(modifier = LayoutWeight(0.66f) + LayoutPadding(4.dp), onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(modifier = LayoutWeight(0.34f) + LayoutPadding(4.dp), onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowAddSpaceBetweenViewsComponent() {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceBetween) {
        Button(onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowSpaceViewsEvenlyComponent() {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowSpaceAroundViewsComponent() {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.SpaceAround) {
        Button(onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowViewsCenteredComponent() {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.Center) {
        Button(onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowViewsArrangedInEndComponent() {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.End) {
        Button(onClick = {}) {
            Text(text = "Button 1",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }

        Button(onClick = {}) {
            Text(text = "Button 2",
                style = TextStyle(fontSize = TextUnit.Companion.Sp(20)))
        }
    }
}

@Composable
fun RowBaselineAlignComponent() {
    Row(modifier = LayoutWidth.Fill) {
        Text(text = "Text 1",
            style = TextStyle(fontSize = TextUnit.Companion.Sp(20), fontStyle = FontStyle.Italic),
            modifier = LayoutGravity.RelativeToSiblings(alignmentLine = FirstBaseline))
        Text(text = "Text 2",
            style = TextStyle(fontSize = TextUnit.Companion.Sp(40), fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold),
            modifier = LayoutGravity.RelativeToSiblings(alignmentLine = FirstBaseline))
    }
}

@Composable
fun RowBaselineUnalignedComponent() {
    Row(modifier = LayoutWidth.Fill) {
        Text(text = "Text 1",
            style = TextStyle(fontSize = TextUnit.Companion.Sp(20), fontStyle = FontStyle.Italic))
        Text(text = "Text 2",
            style = TextStyle(fontSize = TextUnit.Companion.Sp(40), fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold))
    }
}

// Android Studio lets you preview your composable functions within the IDE itself, instead of
// needing to download the app to an Android device or emulator. This is a fantastic feature as you
// can preview all your custom components(read composable functions) from the comforts of the IDE.
// The main restriction is, the composable function must not take any parameters. If your composable
// function requires a parameter, you can simply wrap your component inside another composable
// function that doesn't take any parameters and call your composable function with the appropriate
// params. Also, don't forget to annotate it with @Preview & @Composable annotations.
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