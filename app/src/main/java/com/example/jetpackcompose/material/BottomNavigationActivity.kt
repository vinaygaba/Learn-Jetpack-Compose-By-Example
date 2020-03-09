package com.example.jetpackcompose.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.BottomNavigation
import androidx.ui.material.BottomNavigationItem
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.surface.Card
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcompose.image.TitleComponent

class BottomNavigationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                TitleComponent("This is a simple bottom navigation bar that always shows label")
                Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
                    BottomNavigationAlwaysShowLabelComponent()
                }

                TitleComponent("This is a bottom navigation bar that only shows label for " +
                        "selected item")
                Card(shape = RoundedCornerShape(4.dp), modifier = LayoutPadding(8.dp)) {
                    BottomNavigationOnlySelectedLabelComponent()
                }
            }
        }
    }
}

val listItems = listOf("Games", "Apps", "Movies", "Books")

@Composable
fun BottomNavigationAlwaysShowLabelComponent() {
    var selectedIndex by state { 0 }
    BottomNavigation(modifier = LayoutPadding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(icon = Icons.Filled.Favorite)
                },
                text = {
                    Text(text = label)
                },
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index }
            )
        }
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    var selectedIndex by state { 0 }
    BottomNavigation(modifier = LayoutPadding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(icon = Icons.Filled.Favorite)
                },
                text = {
                    Text(text = label)
                },
                selected = selectedIndex == index,
                onSelected = { selectedIndex = index },
                // Setting this to false causes the label to be show only for the navigation item
                // that is currently selected.
                alwaysShowLabels = false)
        }
    }
}

@Preview
@Composable
fun BottomNavigationAlwaysShowLabelComponentPreview() {
    BottomNavigationAlwaysShowLabelComponent()
}

@Preview
@Composable
fun BottomNavigationOnlySelectedLabelComponentPreview() {
    BottomNavigationOnlySelectedLabelComponent()
}