package com.example.jetpackcompose.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.geometry.Offset
import androidx.ui.graphics.Color
import androidx.ui.graphics.Shadow
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.Surface
import androidx.ui.text.AnnotatedString
import androidx.ui.text.SpanStyle
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.text.style.TextDecoration
import androidx.ui.text.style.TextIndent
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.px
import androidx.ui.unit.sp

class CustomTextActivity : AppCompatActivity() {
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
                    // This is a custom composable declared in this file. It allows us to
                    // configure the text to be rendered on the screen.x
                    CustomStyledText(
                        "This is the default text style"
                    )

                    CustomStyledText(
                        "This text is blue in color",
                        // TextStyle allows you to specify styling configuration for a `Text`
                        // composable
                        style = TextStyle(
                            color = Color.Blue
                        )
                    )

                    CustomStyledText(
                        "This text has a bigger font size",
                        style = TextStyle(
                            fontSize = 30.sp
                        )
                    )

                    CustomStyledText(
                        "This text is bold",
                        style = TextStyle(
                            fontWeight = FontWeight.W700
                        )
                    )

                    CustomStyledText(
                        "This text is italic",
                        style = TextStyle(
                            fontStyle = FontStyle.Italic
                        )
                    )

                    CustomStyledText(
                        "This text is using a custom font family",
                        style = TextStyle(
                            fontFamily = FontFamily.Cursive
                        )
                    )

                    CustomStyledText(
                        "This text has an underline",
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    )

                    CustomStyledText(
                        "This text has a strikethrough line",
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        )
                    )

                    CustomStyledText(
                        "This text will add an ellipsis to the end " +
                                "of the text if the text is longer that 1 line long.",
                        maxLines = 1
                    )

                    CustomStyledText(
                        "This text has a shadow",
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, blurRadius = 10.px,
                                offset = Offset(2f, 2f)
                            )
                        )
                    )

                    // Row is a composable that places its children in a horizontal sequence. You
                    // can think of it similar to a LinearLayout with the horizontal orientation.
                    // In addition, we pass a modifier to the Row composable. You can think of
                    // Modifiers as implementations of the decorators pattern that  are used to
                    // modify the composable that its applied to. In this example, we configure the
                    // Row to occupify the entire available width using Modifier.fillMaxWidth()
                    Row(modifier = Modifier.fillMaxWidth()) {
                        // Text is a predefined composable that does exactly what you'd expect it to -
                        // display text on the screen. It allows you to customize its appearance using
                        // the style property.
                        Text(text = "This text is center aligned",
                            style = TextStyle(
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    // A pre-defined composable that renders a thin line on the screen that makes it
                    // easy to group contents
                    Divider(color = Color.Gray)

                    CustomStyledText(
                        "This text will demonstrate how to justify " +
                                "your paragraph to ensure that the text that ends with a soft " +
                                "line break spreads and takes the entire width of the container",
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        )
                    )

                    CustomStyledText(
                        "This text will demonstrate how to add " +
                                "indentation to your text. In this example, indentation was only " +
                                "added to the first line. You also have the option to add " +
                                "indentation to the rest of the lines if you'd like",
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                            textIndent = TextIndent(firstLine = 30.sp)
                        )
                    )

                    CustomStyledText(
                        "The line height of this text has been " +
                                "increased hence you will be able to see more space between each " +
                                "line in this paragraph.",
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                            lineHeight = 20.sp
                        )
                    )

                    val annotatedString = AnnotatedString {
                        append("This string has style spans")
                        addStyle(style = SpanStyle(color = Color.Red), start = 0, end = 4)
                        addStyle(style = SpanStyle(color = Color.Green), start = 5, end = 21)
                        addStyle(style = SpanStyle(color = Color.Blue), start = 22, end = 27)
                    }
                    Text(annotatedString, modifier = Modifier.padding(16.dp))
                    // A pre-defined composable that renders a thin line on the screen that makes it
                    // easy to group contents
                    Divider(color = Color.Gray)

                    // Surface is a composable provided to fulfill the needs of the "Surface"
                    // metaphor from the Material Design specification. It's generally used to
                    // change the background color, add elevation, clip or add background shape
                    // to its children composables.
                    Surface(color = Color.Yellow) {
                        Text(
                            text = "This text has a background color",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
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
fun CustomStyledText(displayText: String, style: TextStyle? = null, maxLines: Int? = null) {
    // We should think of composable functions to be similar to lego blocks - each composable
    // function is in turn built up of smaller composable functions. Here, the Text() function is
    // pre-defined by the Compose UI library; you call that function to declare and render text
    // in your app.
    Text(
        text = displayText,
        modifier = Modifier.padding(16.dp),
        style = style ?: TextStyle.Default,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines ?: Int.MAX_VALUE
    )
    // A pre-defined composable that renders a thin line on the screen that makes it easy to
    // group contents
    Divider(color = Color.Gray)
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
@Preview
@Composable
fun CustomStyledTextPreview() {
    CustomStyledText(
        "This is preview text",
        maxLines = 2,
        style = TextStyle(
            color = Color.Red,
            fontWeight = FontWeight.W900,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            textAlign = TextAlign.Justify
        )
    )
}
