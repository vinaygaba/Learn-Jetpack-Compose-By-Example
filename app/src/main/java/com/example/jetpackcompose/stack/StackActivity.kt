package com.example.jetpackcompose.stack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.image.LocalResourceImageComponent

class StackActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            StackComponent()
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun StackComponent() {
    Stack(modifier = LayoutWidth.Fill + LayoutHeight(200.dp)) {
        LocalResourceImageComponent(resId = R.drawable.lenna)
        Surface(color = Color.Gray) {
            Text("Title", style = TextStyle(fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W900, fontSize = 14.sp), modifier = LayoutPadding(16.dp)
                    + LayoutWidth.Fill
            )
        }
    }
}