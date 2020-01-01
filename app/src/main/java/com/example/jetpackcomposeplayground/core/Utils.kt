package com.example.jetpackcomposeplayground.core

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.WithDensity
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource

fun getPersonList() = listOf<Person>(
    Person("Grace Hopper", 25),
    Person("Ada Lovelace", 29),
    Person("John Smith", 28),
    Person("Elon Musk", 41),
    Person("Will Smith", 31),
    Person("Robert James", 42),
    Person("Anthony Curry", 91),
    Person("Kevin Jackson", 22)
)

data class Person(
    val name: String,
    val age: Int
)

@Composable
fun VectorImageButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id = id)
        }
    }
}

@Composable
fun VectorImage(modifier: Modifier = Modifier.None, @DrawableRes id: Int, tint: Color = Color.Transparent) {
    val vector = +vectorResource(id)
    WithDensity {
        Container(
            modifier = modifier wraps Size(vector.defaultWidth.toDp(), vector.defaultHeight.toDp())
        ) {
            DrawVector(vector, tint)
        }
    }
}