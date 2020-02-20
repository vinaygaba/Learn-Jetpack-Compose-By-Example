package com.example.jetpackcomposeplayground.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.VerticalScroller
import androidx.ui.geometry.Offset
import androidx.ui.graphics.Color
import androidx.ui.graphics.Shadow
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.material.Divider
import androidx.ui.material.surface.Surface
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
            // child views
            VerticalScroller {
                Column {
                    CustomStyledText(
                        "This is the default text style"
                    )

                    CustomStyledText(
                        "This text is blue in color",
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

                    Row(modifier = LayoutWidth.Fill) {
                            Text(text = "This text is center aligned",
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                ),
                                modifier = LayoutPadding(16.dp)
                            )
                    }

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
                    Text(annotatedString, modifier = LayoutPadding(16.dp))
                    Divider(color = Color.Gray)

                    Surface(color = Color.Yellow) {
                        Text(text = "This text has a background color", modifier = LayoutPadding(16.dp))
                    }
                }
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions.
@Composable
fun CustomStyledText(displayText: String, style: TextStyle? = null, maxLines: Int? = null) {
    // We should think of composable functions to be similar to lego blocks - each composable
    // function is in turn built up of smaller composable functions. Here, the Text() function is
    // pre-defined by the Compose UI library; you call that function to declare a text element
    // in your app.
    Text(
        text = displayText,
        modifier = LayoutPadding(16.dp),
        style = style,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines ?: Int.MAX_VALUE
    )
    Divider(color = Color.Gray)
}

// Android Studio lets you preview your composable functions within the IDE itself, instead of
// needing to download the app to an Android device or emulator. This is a fantastic feature as you
// can preview all your custom components(read composable functions) from the comforts of the IDE.
// The main restriction is, the composable function must not take any parameters. If your composable
// function requires a parameter, you can simply wrap your component inside another composable
// function that doesn't take any parameters and call your composable function with the appropriate
// params. Also, don't forget to annotate it with @Preview & @Composable annotations.
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