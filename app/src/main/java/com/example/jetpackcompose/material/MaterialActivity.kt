package com.example.jetpackcompose.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.SimpleImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.selection.ToggleableState
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.image.TitleComponent

class MaterialActivity : AppCompatActivity() {

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
                    TitleComponent("This is a simple Material card")
                    MaterialCardComponent()

                    TitleComponent("This is a loading progress indicator ")
                    MaterialLinearProgressIndicatorComponent()

                    TitleComponent("This is a determinate progress indicator")
                    MaterialDeterminateLinearProgressIndicatorComponent()

                    TitleComponent("This is a loading circular progress indicator")
                    MaterialCircularProgressIndicatorComponent()

                    TitleComponent("This is a determinate circular progress indicator")
                    MaterialDeterminateCircularProgressIndicatorComponent()

                    TitleComponent("This is a material Snackbar")
                    MateriaSnackbarComponent()

                    TitleComponent("This is a non-discrete slider")
                    MateriaContinousSliderComponent()

                    TitleComponent("This is a discrete slider")
                    MaterialDiscreteSliderComponent()

                    TitleComponent("This is a checkbox that represents two states")
                    MaterialCheckboxComponent()

                    TitleComponent("This is a checkbox that represents three states")
                    MaterialTriStateCheckboxComponent()

                    TitleComponent("This is a radio button group")
                    MaterialRadioButtonGroupComponent()

                    TitleComponent("This is a switch component")
                    MaterialSwitchComponent()

                    TitleComponent("This is how you add a ripple effect to a view")
                    MaterialRippleComponent()
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
fun MaterialCardComponent() {
    val resources = ContextAmbient.current.resources
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
        ListItem(text = {
            Text(text = "Title")
        }, secondaryText = {
            Text(text = "Subtitle")
        }, icon = {
            Container(width = 48.dp, height = 48.dp) {
                SimpleImage(imageFromResource(resources, R.drawable.lenna))
            }
        })
    }
}

@Composable
fun MaterialCheckboxComponent() {
    var checked by state { false}
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        Row(modifier = LayoutPadding(16.dp)) {
            Checkbox(checked = checked,
                onCheckedChange = {
                    checked = !checked
                })
            Text(text = "Use Jetpack Compose", modifier = LayoutPadding(start = 8.dp))
        }
    }
}

@Composable
fun MaterialTriStateCheckboxComponent() {
    val toggleableStateArray = listOf(ToggleableState.Off, ToggleableState.On, ToggleableState.Indeterminate)
    var counter by state { 0 }
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        Row(modifier = LayoutPadding(16.dp)) {
            TriStateCheckbox(
                value = toggleableStateArray[counter % 3],
                onClick = {
                    counter++
                })
            Text(text = "Use Jetpack Compose", modifier = LayoutPadding(start = 8.dp))
        }

    }
}

@Composable
fun MaterialRadioButtonGroupComponent() {
    var selected by state { "Android"}
    val radioGroupOptions = listOf<String>("Android", "iOS", "Windows")
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        RadioGroup(options = radioGroupOptions, selectedOption = selected, onSelectedChange = {
            selected = it
        })
    }
}

@Composable
fun MaterialLinearProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        Row(modifier = LayoutPadding(16.dp)) {
            // Has fixed width as per Material spec - 240dp
            LinearProgressIndicator()
        }
    }
}

@Composable
fun MaterialDeterminateLinearProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        Row(modifier = LayoutPadding(16.dp)) {
            // Has fixed width as per Material spec - 240dp
            LinearProgressIndicator(progress = 0.3f)
        }
    }
}

@Composable
fun MaterialCircularProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        CircularProgressIndicator()
    }
}

@Composable
fun MaterialDeterminateCircularProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        CircularProgressIndicator(progress = 0.5f)
    }
}

@Composable
fun MateriaSnackbarComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
        Snackbar(text = {
            Text(text = "I'm a very nice Snackbar")
        }, action = {
            Text(text = "OK", style = TextStyle(color = MaterialTheme.colors().secondary))
        })
    }

}

@Composable
fun MateriaContinousSliderComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
        Slider(position = SliderPosition(0.2f))
    }
}

@Composable
fun MaterialDiscreteSliderComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
        Slider(position = SliderPosition(initial = 0f, valueRange = 0f..10f, steps = 5))
    }
}

@Composable
fun MaterialSwitchComponent() {
    var checked by state { false}
    Card(shape = RoundedCornerShape(4.dp),
        modifier = LayoutPadding(8.dp) + LayoutWidth.Fill,
        color = Color(249, 249, 249)) {
        Row(modifier = LayoutPadding(16.dp)) {
            Switch(checked = checked, onCheckedChange = {
                checked = !checked
            })
            Text(text = "Enable Dark Mode", modifier = LayoutPadding(start = 8.dp))
        }
    }
}

@Composable
fun MaterialRippleComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp) + LayoutWidth.Fill) {
        Ripple(bounded = true) {
            Clickable(onClick = {}) {
                Box(backgroundColor = Color.LightGray, shape = RoundedCornerShape(4.dp)) {
                    Text(text = "Click Me", modifier = LayoutPadding(16.dp), style = TextStyle(
                        fontSize = TextUnit.Companion.Sp(12), fontFamily = FontFamily.Serif
                    ))
                }

            }
        }
    }
}

