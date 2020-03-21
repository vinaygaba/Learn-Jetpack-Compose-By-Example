package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.unit.dp
import com.example.jetpackcompose.core.colors

class FixedActionButtonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldWithBottomBarAndCutout()
        }
    }
}

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    val scaffoldState = remember { ScaffoldState() }
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val fabShape = RoundedCornerShape(50)

    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = { TopAppBar(title = { Text("Scaffold Examples") }) },
        bottomAppBar = { fabConfiguration ->
            BottomAppBar(fabConfiguration = fabConfiguration, cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = fabShape,
                color = MaterialTheme.colors().secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(icon = Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButtonPosition = Scaffold.FabPosition.CenterDocked,
        bodyContent = { modifier ->
            VerticalScroller {
                Column(modifier) {
                    repeat(100) {
                        Card(color = colors[it % colors.size],
                            shape = RoundedCornerShape(8.dp),
                            modifier = LayoutPadding(8.dp)) {
                            Spacer(modifier = LayoutWidth.Fill + LayoutHeight(200.dp))
                        }
                    }
                }
            }
        }
    )
}