package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Snackbar
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
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
            // ScrollableColumn is a composable that adds the ability to scroll through the
            // child views. We should think of composable functions to be similar to lego blocks -
            // each composable function is in turn built up of smaller composable functions
            ScrollableColumn {
                // Column is a composable that places its children in a vertical sequence.
                Column {
                    // Title Component is a custom composable that we created which is capable of
                    // rendering text on the screen in a certain font style & text size.
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
                    MaterialSnackbarComponent()

                    TitleComponent("This is a non-discrete slider")
                    MaterialContinousSliderComponent()

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
    // Ambient is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use Ambients. In this example, we use the
    // AmbientContext to get hold of the Context object. In order to get access to the latest
    // value of the Ambient, use the "current" property eg - AmbientContext.current. Some other
    // examples of common Ambient's are AmbientTextInputService, AmbientDensity,
    // CoroutineAmbientContext, etc.
    val resources = AmbientContext.current.resources

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable.
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        // ListItem is a predefined composable that is a Material Design implementation of [list
        // items](https://material.io/components/lists). This component can be used to achieve the
        // list item templates existing in the spec
        ListItem(text = {
            Text(text = "Title")
        }, secondaryText = {
            Text(text = "Subtitle")
        }, icon = {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation. 
            // In addition we also pass a few modifiers to it.
            Column(modifier = Modifier.preferredWidth(48.dp).preferredHeight(48.dp)) {
                Image(bitmap = imageFromResource(resources, R.drawable.landscape))
            }
        })
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialCheckboxComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether the checkbox is checked. Any composable that reads the value of "checked"
    // will be recomposed any time the value changes. This ensures that only the composables that
    // depend on this will be redraw while the rest remain unchanged. This ensures efficiency and
    // is a performance optimization. It is inspired from existing frameworks like React.
    var checked by remember { mutableStateOf(false) }

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // Row is a composable that places its children in a horizontal sequence. You can think of it
        // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
        // to the Row composable.
        Row(modifier = Modifier.padding(16.dp)) {
            // A pre-defined composable that's capable of rendering a checkbox with 2 values - on,
            // & off. It honors the Material Design specification.
            Checkbox(checked = checked,
                onCheckedChange = {
                    checked = !checked
                })
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Use Jetpack Compose", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialTriStateCheckboxComponent() {
    val toggleableStateArray =
        listOf(ToggleableState.Off, ToggleableState.On, ToggleableState.Indeterminate)
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the counter. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var counter by remember { mutableStateOf(0) }

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // Row is a composable that places its children in a horizontal sequence. You can think of it
        // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
        // to the Row composable.
        Row(modifier = Modifier.padding(16.dp)) {
            // A pre-defined checkbox composable that's capable of rendering 3 values - on, off &
            // indeterminate. It honors the Material Design specification.
            TriStateCheckbox(
                state = toggleableStateArray[counter % 3],
                onClick = {
                    counter++
                })
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Use Jetpack Compose", modifier = Modifier.padding(start = 8.dp))
        }

    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialRadioButtonGroupComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether a radio button is selected. Any composable that reads the value of
    // "selected" variable will be recomposed any time the value changes. This ensures that only the
    // composables that depend on this will be redraw while the rest remain unchanged. This ensures
    // efficiency and is a performance optimization. It is inspired from existing frameworks like
    // React.
    var selected by remember { mutableStateOf("Android") }

    val radioGroupOptions = listOf<String>("Android", "iOS", "Windows")
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // A pre-defined composable that's capable of rendering a radio group. It honors the
        // Material Design specification.
        val onSelectedChange = { text: String ->
            selected = text
        }
        Column {
            radioGroupOptions.forEach { text ->
                Row(Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selected),
                        onClick = { onSelectedChange(text) }
                    )
                    .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selected),
                        onClick = { onSelectedChange(text) }
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
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
fun MaterialLinearProgressIndicatorComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // Row is a composable that places its children in a horizontal sequence. You can think of it
        // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
        // to the Row composable.
        Row(modifier = Modifier.padding(16.dp)) {
            // A pre-defined composable that's capable of rendering a progress indicator. It honors
            // the Material Design specification. It has fixed width as per Material spec - 240dp
            LinearProgressIndicator()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialDeterminateLinearProgressIndicatorComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // Row is a composable that places its children in a horizontal sequence. You can think of it
        // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
        // to the Row composable.
        Row(modifier = Modifier.padding(16.dp)) {
            // A pre-defined composable that's capable of rendering a progress indicator. It honors
            // the Material Design specification. It has fixed width as per Material spec - 240dp
            LinearProgressIndicator(progress = 0.3f)
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialCircularProgressIndicatorComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialDeterminateCircularProgressIndicatorComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(
            progress = 0.5f,
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialSnackbarComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable.
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        // A pre-defined composable that's capable of rendering a Snackbar. It honors the Material
        // Design specification.
        Snackbar(text = {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "I'm a very nice Snackbar")
        }, action = {
            Text(text = "OK", style = TextStyle(color = MaterialTheme.colors.secondary))
        })
    }

}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialContinousSliderComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the slider. Any composable that reads the value of "sliderValue"
    // variable will be recomposed any time the value changes. This ensures that only the
    // composables that depend on this will be redraw while the rest remain unchanged. This ensures
    // efficiency and is a performance optimization. It is inspired from existing frameworks like
    // React.
    var sliderValue by remember { mutableStateOf(0f) }

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable.
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        // A pre-defined composable that's capable of rendering a slider. It
        // honors the Material Design specification.
        Slider(value = sliderValue, onValueChange = { newValue ->
            sliderValue = newValue
        })
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialDiscreteSliderComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the slider. Any composable that reads the value of "sliderValue"
    // variable will be recomposed any time the value changes. This ensures that only the
    // composables that depend on this will be redraw while the rest remain unchanged. This ensures
    // efficiency and is a performance optimization. It is inspired from existing frameworks like
    // React.
    var sliderValue by remember { mutableStateOf(0f) }

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable.
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        // A pre-defined composable that's capable of rendering a slider. It honors the Material
        // Design specification. In this example, we create a discrete slider with fixed steps.
        Slider(
            value = sliderValue,
            valueRange = 0f..10f,
            steps = 4,
            onValueChange = { sliderValue = it })
    }

    Text(
        text = "Slider value is %.1f".format(sliderValue),
        modifier = Modifier.padding(8.dp)
    )
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialSwitchComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of whether the checkbox is checked. Any composable that reads the value of "checked"
    // variable will be recomposed any time the value changes. This ensures that only the
    // composables that depend on this will be redraw while the rest remain unchanged. This ensures
    // efficiency and is a performance optimization. It is inspired from existing frameworks like
    // React.
    var checked by remember { mutableStateOf(false) }

    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        backgroundColor = Color(249, 249, 249)
    ) {
        // Row is a composable that places its children in a horizontal sequence. You can think of it
        // similar to a LinearLayout with the horizontal orientation. In addition, we pass a modifier
        // to the Row composable.
        Row(modifier = Modifier.padding(16.dp)) {
            // A pre-defined composable that's capable of rendering a switch. It honors the Material
            // Design specification.
            Switch(checked = checked, onCheckedChange = {
                checked = !checked
            })
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Enable Dark Mode", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MaterialRippleComponent() {
    // Card composable is a predefined composable that is meant to represent the card surface as
    // specified by the Material Design specification. We also configure it to have rounded
    // corners and apply a modifier.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In the example below, we add a padding of
    // 8dp to the Card composable. In addition, we configure it out occupy the entire available
    // width using the Modifier.fillMaxWidth() modifier.
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        // Column with clickable modifier wraps the child composable and enables it to react to a 
        // click through the onClick callback similar to the onClick listener that we are accustomed 
        // to on Android. In order to show a ripple effect, we set indication of Modifier.clickable 
        // with a RippleIndication.
        Column(
            modifier = Modifier.clickable(onClick = {}, indication = rememberRipple())
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(
                text = "Click Me",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(fontSize = TextUnit.Sp(12), fontFamily = FontFamily.Serif)
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
@Preview("MaterialCardComponent")
@Composable
fun MaterialCardComponentPreview() {
    MaterialCardComponent()
}

@Preview("MaterialCheckboxComponent")
@Composable
fun MaterialCheckboxComponentPreview() {
    MaterialCheckboxComponent()
}

@Preview("MaterialTriStateCheckboxComponent")
@Composable
fun MaterialTriStateCheckboxComponentPreview() {
    MaterialTriStateCheckboxComponent()
}

@Preview("MaterialRadioButtonGroupComponent")
@Composable
fun MaterialRadioButtonGroupComponentPreview() {
    MaterialRadioButtonGroupComponent()
}

@Preview("MaterialLinearProgressIndicatorComponent")
@Composable
fun MaterialLinearProgressIndicatorComponentPreview() {
    MaterialLinearProgressIndicatorComponent()
}

@Preview("MaterialDeterminateLinearProgressIndicatorComponent")
@Composable
fun MaterialDeterminateLinearProgressIndicatorComponentPreview() {
    MaterialDeterminateLinearProgressIndicatorComponent()
}

@Preview("MaterialCircularProgressIndicatorComponent")
@Composable
fun MaterialCircularProgressIndicatorComponentPreview() {
    MaterialCircularProgressIndicatorComponent()
}

@Preview("MaterialDeterminateCircularProgressIndicatorComponent")
@Composable
fun MaterialDeterminateCircularProgressIndicatorComponentPreview() {
    MaterialDeterminateCircularProgressIndicatorComponent()
}


@Preview("MaterialSnackbarComponent")
@Composable
fun MaterialSnackbarComponentPreview() {
    MaterialSnackbarComponent()
}

@Preview("MaterialContinousSliderComponent")
@Composable
fun MaterialContinousSliderComponentPreview() {
    MaterialContinousSliderComponent()
}

@Preview("MaterialDiscreteSliderComponent")
@Composable
fun MaterialDiscreteSliderComponentPreview() {
    MaterialDiscreteSliderComponent()
}

@Preview("MaterialSwitchComponent")
@Composable
fun MaterialSwitchComponentPreview() {
    MaterialSwitchComponent()
}

@Preview("MaterialRippleComponent")
@Composable
fun MaterialRippleComponentPreview() {
    MaterialRippleComponent()
}
